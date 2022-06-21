class Node:
    right = None
    left = None
    data = None

    def __init__(self, data=None):
        self.right = None
        self.left = None
        self.data = data

    def __str__(self):
        s = str(self.data) + ""
        if self.left is not None:
            s = s + " L:(" + str(self.left) + ")"
        if self.right is not None:
            s = s + " R:(" + str(self.right) + ")"
        return s

    def __iter__(self):
        return inorder(self)


class binarysearchtree:
    name = None
    left = None
    right = None
    root = None

    def __init__(self, name, root):
        self.right = Node()
        self.left = Node()
        self.root = root
        self.name = name

    def add_all(self, *lst):
        self.root = None
        for num in lst:
            self.root = self.insert(self.root, num)

    def insert(self, node, data):
        if node is None:
            return Node(data)
        if data < node.data:
            node.left = self.insert(node.left, data)
        else:
            node.right = self.insert(node.right, data)
        return node

    def __iter__(self):
        return inorder(self)

    def __str__(self):
        return "[" + self.name + "]" + str(self.root)

def inorder(t):
    if t:
        for y in inorder(t.left):
            yield y
        yield t.data

        for y in inorder(t.right):
            yield y


if __name__ == "__main__":
    t1 = binarysearchtree(name="Oak", root=Node())
    t2 = binarysearchtree(name="Birch", root=Node())
    t1.add_all(5, 3, 9, 0)
    t2.add_all(1, 0, 10, 2, 7)
    for x in t1.root:
        print(x)
    for x in t2.root:
        print(x)
    tree = binarysearchtree(name="Oak", root=Node())
    tree.add_all(5, 3, 9, 0)  # adds the elements in the order 5, 3, 9, and then 0
    print(t2)
