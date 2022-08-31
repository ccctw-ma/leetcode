#! /usr/bin/env -S python3 -O
# ! /usr/bin/env -S python3
# ! C:\Users\13405\AppData\Local\Programs\Python\Python310\python.exe C:/Users/13405/PycharmProjects/leetcode/IPIN/demo.py interactive
import os
import sys
import lzma
import requests
import time
from parse import parse
import yaml
from evaalapi import statefmt, estfmt

trialsdir = "trials/"
globinifn = "evaalapi.yaml"
server = "https://evaal.aaloa.org/evaalapi/"
trialname = "demo"
# trialname = "test"


def do_req(req, n=2):
    r = requests.get(server + trialname + req)
    print("\n==>  GET " + req + " --> " + str(r.status_code))
    if False and r.headers['content-type'].startswith("application/x-xz"):
        l = lzma.decompress(r.content).decode('ascii').splitlines()
    else:
        l = r.text.splitlines()
    if len(l) <= 2 * n + 1:
        print(r.text + '\n')
    else:
        print('\n'.join(l[:n]
                        + ["   ... ___%d lines omitted___ ...   " % len(l)]
                        + l[-n:] + [""]))

    return (r)


def input_or_sleep(maxw):
    r = requests.get(server + trialname + "/state")
    s = parse(statefmt, r.text)
    trialts = s['trialts']
    rem = s['rem']
    if trialts > 0:
        print("Trial running, %.3f seconds to timeout" % rem)
    else:
        if trialts == 0:
            print("Trial not started")
        else:  # trialts < 0
            print("Trial timed out %f seconds ago" % -rem)
        rem = 0
    if interactive:
        input("Press Enter to proceed\n")
    else:
        if rem > 0:
            w = min(maxw, max(0, rem - s['S'] / 2))
        else:
            w = maxw
        print("Waiting for %.1fs...\n" % w)
        time.sleep(w)


def demo(interactive, maxw):
    ## First of all, reload
    if reloadable:
        print("This is a reloadable trial, so let's start by reloading it to be sure")
        r = do_req("/reload")
        print("For official trials, this reload step must be skipped\n")

    ## Check initial state
    print("First, check the state of the trial")
    r = do_req("/state")
    print("The return code is the trial state, whose meaning is:")
    s = parse(statefmt, r.text)
    print(s.named)
    print("""'trialts' is 0, as expected
'rem' is -1, meaning that the trial has not started yet
'pos' is set to the initial position
""")

    ## Get first 1s worth of data
    input_or_sleep(maxw)
    print("""This is the first data we request, and we are at the start of trial,
where the position is known, so we do not bother sending a position estimate
and moreover we ask for a longer-than-usual horizon, that is, 1s""")
    r = do_req("/nextdata?horizon=1")

    ## Look at remaining time
    input_or_sleep(maxw)
    print("""You can freely intermix "state" requests in the flow of "nextdata"
requests.  This may be useful for consistency check, including timeout checks.  This is
possible by looking at the state of the trial:""")
    r = do_req("/state")
    print("The return code is the trial state, whose meaning is:")
    s = parse(statefmt, r.text)
    print(s.named)
    print("""'trialts' is 1.0, as expected
'rem' is the remaining time until timeout
""")

    ## Set estimates
    input_or_sleep(maxw)
    print("""From now on we keep sending an estimate and ask for new data
in steps of 0.5s.  Since this is the default horizon, we do not set horizon
explicitely in the request, but we only set the estimated position.
Note that the positions are just arbitrary numbers in this demo.""")
    for pos in range(5):
        r = do_req("/nextdata?position=%.1f,%.1f,%.1f" % (pos + .1, pos + .2, pos + .3))
        input_or_sleep(maxw)

    ## Get estimates
    print(
        """You can get the list of estimates set so far at any moment, even though this is mostly useful at the end of trial.  Here it is how it looks after setting 5 estimates:""")
    r = do_req("/estimates", 3)
    print("Parsing the last estimate line we get:")
    s = parse(estfmt, r.text.splitlines()[-1])
    print(s.named)

    ## Get log
    input_or_sleep(maxw)
    print(
        """You can get a log of the trial session, including all received data.  Again, this is mostly useful at end of trial.  Here it is how it looks at this point:""")
    r = do_req("/log", 12)

    ## We finish here
    print("Demo stops here")


################################################################

if __name__ == '__main__':
    interactive = False
    trialinifn = trialsdir + trialname + ".yaml"
    with open(globinifn, 'r') as inif:
        ini = yaml.safe_load(inif)
    print(ini)
    global reloadable
    reloadable = bool(ini[trialname]['reloadable'])
    print(trialname, reloadable)
    print("# Running %s demo test suite\n" % ("interactive" if interactive else "auto"))
    maxw = 3 if trialname == 'slowtest' else 10
    demo(interactive, maxw)
    exit(0)

# Local Variables:
# mode: python
# comment-column: 40
# fill-column: 100
# End:
