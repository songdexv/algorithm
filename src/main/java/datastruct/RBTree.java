package datastruct;

/**
 * Created by songdexv on 2018/4/3.
 */
public class RBTree<T extends Comparable<T>> {
    private RBNode<T> root; //根节点
    private static final boolean RED = false; //定义红黑树标志
    private static final boolean BLACK = true;

    public class RBNode<T extends Comparable<T>> {
        boolean color; //颜色
        T key;//关键字(键值)
        RBNode<T> left; //左子节点
        RBNode<T> right; //右子节点
        RBNode<T> parent; //父节点

        public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey() {
            return key;
        }

        public String toString() {
            return "" + key + (this.color == RED ? "R" : "B");
        }
    }

    public RBTree() {
        root = null;
    }

    public RBNode<T> parentOf(RBNode<T> node) { //获得父节点
        return node != null ? node.parent : null;
    }

    public void setParent(RBNode<T> node, RBNode<T> parent) { //设置父节点
        if (node != null) {
            node.parent = parent;
        }
    }

    public boolean colorOf(RBNode<T> node) { //获得节点的颜色
        return node != null ? node.color : BLACK;
    }

    public boolean isRed(RBNode<T> node) { //判断节点的颜色
        return (node != null) && (node.color == RED) ? true : false;
    }

    public boolean isBlack(RBNode<T> node) {
        return !isRed(node);
    }

    public void setRed(RBNode<T> node) { //设置节点的颜色
        if (node != null) {
            node.color = RED;
        }
    }

    public void setBlack(RBNode<T> node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    public void setColor(RBNode<T> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    /***************** 前序遍历红黑树 *********************/
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(RBNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /***************** 中序遍历红黑树 *********************/
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(RBNode<T> tree) {
        if (tree != null) {
            preOrder(tree.left);
            System.out.print(tree.key + " ");
            preOrder(tree.right);
        }
    }

    /***************** 后序遍历红黑树 *********************/
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(RBNode<T> tree) {
        if (tree != null) {
            preOrder(tree.left);
            preOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    /**************** 查找红黑树中键值为key的节点 ***************/
    public RBNode<T> search(T key) {
        return search(root, key);
        //      return search2(root, key); //使用递归的方法，本质一样的
    }

    private RBNode<T> search(RBNode<T> x, T key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x;
            }
        }
        return x;
    }

    //使用递归
    private RBNode<T> search2(RBNode<T> x, T key) {
        if (x == null) {
            return x;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return search2(x.left, key);
        } else if (cmp > 0) {
            return search2(x.right, key);
        } else {
            return x;
        }
    }

    /**************** 查找最小节点的值  **********************/
    public T minValue() {
        RBNode<T> node = minNode(root);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    private RBNode<T> minNode(RBNode<T> tree) {
        if (tree == null) {
            return tree;
        }
        while (tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }

    /******************** 查找最大节点的值 *******************/
    public T maxValue() {
        RBNode<T> node = maxNode(root);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    private RBNode<T> maxNode(RBNode<T> tree) {
        if (tree == null) {
            return null;
        }
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree;
    }

    /********* 查找节点x的后继节点,即大于节点x的最小节点 ***********/
    public RBNode<T> successor(RBNode<T> x) {
        if (x.right != null) {
            return minNode(x.right);
        }
        RBNode<T> y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = x.parent;
        }
        return y;
    }

    /********* 查找节点x的前序节点,即小于节点x的最大节点 ***********/
    public RBNode<T> predecessor(RBNode<T> x) {
        if (x.left != null) {
            return maxNode(x.left);
        }
        RBNode<T> y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = x.parent;
        }
        return y;
    }

    /*************对红黑树节点x进行左旋操作 ******************/
    /*
     * 左旋示意图：对节点x进行左旋
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(RBNode<T> x) {
        //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else {
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        //3. 将y的左子节点设为x，将x的父节点设为y
        y.left = x;
        x.parent = y;
    }

    /*************对红黑树节点y进行右旋操作 ******************/
    /*
     * 左旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RBNode<T> y) {
        //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        //2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
        x.parent = y.parent;
        if (y.parent == null) {
            this.root = x;
        } else {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        //3. 将x的右子节点设为y，将y的父节点设为x
        x.right = y;
        y.parent = x;
    }

    /*********************** 向红黑树中插入节点 **********************/
    public void insert(T key) {
        RBNode<T> node = new RBNode<T>(key, RED, null, null, null);
        if (node != null) {
            insert(node);
        }
    }

    //将节点插入到红黑树中，这个过程与二叉搜索树是一样的
    private void insert(RBNode<T> node) {
        RBNode<T> current = null; //表示最后node的父节点
        RBNode<T> x = this.root; //用来向下搜索用的
        //1. 找到插入的位置
        while (x != null) {
            current = x;
            int cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            }
        }
        //找到了位置，将当前current作为node的父节点
        node.parent = current;
        //2. 接下来判断node是插在左子节点还是右子节点
        if (current != null) {
            int cmp = node.key.compareTo(current.key);
            if (cmp < 0) {
                current.left = node;
            } else {
                current.right = node;
            }
        } else {
            this.root = node;
        }
        //3. 将它重新修整为一颗红黑树
        insertFixUp(node);
    }

    private void insertFixUp(RBNode<T> node) {
        RBNode<T> parent, gparent; //定义父节点和祖父节点
        //需要修整的条件：父节点存在，且父节点的颜色是红色
        while ((parent = parentOf(node)) != null && isRed(parent)) {
            gparent = parentOf(parent);//获得祖父节点
            //若父节点是祖父节点的左子节点，下面else与其相反
            if (parent == gparent.left) {
                //获得叔叔节点
                RBNode<T> uncle = gparent.right;
                //case1: 叔叔节点也是红色
                if (uncle != null && isRed(uncle)) {
                    //把父节点和叔叔节点涂黑
                    setBlack(parent);
                    setBlack(uncle);
                    //把祖父节点涂红
                    setRed(gparent);
                    //将位置放到祖父节点处
                    node = gparent;
                    //继续while，重新判断
                    continue;
                }
                //case2: 叔叔节点是黑色，且当前节点是右子节点
                if (node == parent.right) {
                    //从父节点处左旋
                    leftRotate(parent);
                    //然后将父节点和自己调换一下，为下面右旋做准备
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                //case3: 叔叔节点是黑色，且当前节点是左子节点
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            } else {
                //获得叔叔节点
                RBNode<T> uncle = gparent.left;
                //case1: 叔叔节点也是红色
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                //case2: 叔叔节点是黑色的，且当前节点是左子节点
                if (node == parent.left) {
                    rightRotate(parent);
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                //case3: 叔叔节点是黑色的，且当前节点是右子节点
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            }
        }
        //将根节点设置为黑色
        setBlack(this.root);
    }


}