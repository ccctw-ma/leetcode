from collections import defaultdict
from typing import List


def findRestaurant(list1: List[str], list2: List[str]) -> List[str]:
    res = []
    restaurant_map = dict()
    for i, s in enumerate(list1):
        restaurant_map[s] = i
    max_index = len(list1) + len(list2) + 1
    for i, s in enumerate(list2):
        if s in restaurant_map:
            if i + restaurant_map[s] < max_index:
                max_index = i + restaurant_map[s]
                res = [s]
            elif i + restaurant_map[s] == max_index:
                res.append(s)
    return res


if __name__ == '__main__':
    print(findRestaurant(list1=["Shogun", "Tapioca Express", "Burger King", "KFC"],
                         list2=["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]))
