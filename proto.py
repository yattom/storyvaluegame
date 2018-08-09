# encoding: utf-8

from collections import namedtuple
from enum import Enum
from random import randint

Item = namedtuple('Item', ('name', 'deadline', 'revenue', 'force', 'difficulty'))
BacklogEntry = namedtuple('BacklogEntry', ('item', 'state'))

def dice():
    return randint(1, 7)

class Difficulty(Enum):
    A = 0
    B = 0
    C = 0

class ProductBacklog:
    TODO = 0
    DONE = 1
    RELEASED = 2

    def __init__(self, items):
        self._backlog = [BacklogEntry(i, ProductBacklog.TODO) for i in items]

    def get_top_item(self):
        for e in self._backlog:
            if e.state == ProductBacklog.TODO:
                return e.item

    def done(self, item):
        for i, e in enumerate(self._backlog):
            if e.item == item:
                self._backlog[i] = e._replace(state=ProductBacklog.DONE)
                return
        print(item)
        print(self._backlog)
        raise ValueError('item is not in backlog')


class Sprint:
    FAIL_CHANCE = { Difficulty.A: 1, Difficulty.B: 2, Difficulty.C: 3 }
    def __init__(self, num, controller):
        self._sprint_num = num
        self._controller = controller

    def work_on(self, backlog):
        todo_item = backlog.get_top_item()
        if Sprint.FAIL_CHANCE[todo_item.difficulty] > dice():
            backlog.done(todo_item)



class Controller:
    pass


def build_items():
    return [
        Item("I01", None, 3, 0, Difficulty.A),
        Item("I02", None, 3, 0, Difficulty.A),
        Item("I03", None, 3, 0, Difficulty.A),
        Item("I04", None, 3, 0, Difficulty.A),
        Item("I05", None, 3, 0, Difficulty.A),
        Item("I06", None, 3, 0, Difficulty.A),
        Item("I07", None, 3, 0, Difficulty.A),
        Item("I08", None, 3, 0, Difficulty.A),
        Item("I09", None, 3, 0, Difficulty.A),
        Item("I10", None, 3, 0, Difficulty.A),
    ]

def main():
    controller = Controller()
    backlog = ProductBacklog(build_items())
    for i in range(10):
        sprint = Sprint(i + 1, controller)
        sprint.work_on(backlog)
    print(backlog._backlog)

if __name__=='__main__':
    main()
