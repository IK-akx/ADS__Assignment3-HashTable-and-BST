package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Node<K, V>> {
    private Node<K, V> root;
    private int size;

    public class Node<K, V> {
        private K key;
        private V val;
        private Node left;
        private Node right;

        public Node(K key, V val){
            this.key = key;
            this.val = val;
        }

        public K getKey(){
            return key;
        }

        public V getVal(){
            return val;
        }
    }

    public BST(){
        size = 0;
    }

    public int size(){
        return size;
    }

    public void put(K key, V val){
        if(root == null){
            root = new Node(key, val);

            size++;
            return;
        }

        Node<K, V> current = root;
        while(current != null){
            if(key.compareTo(current.key) == 0){
                current.val = val;

                return;
            }

            if(key.compareTo(current.key) < 0){
                if(current.left == null){
                    current.left = new Node(key, val);

                    size++;
                    return;
                }
                current = current.left;
            }
        }
    }

    public V get(K key){
        Node<K, V> current = root;
        while(current != null){
            int cmp = key.compareTo(current.key);
            if(cmp < 0){
                current = current.left;
            }
            else if(cmp > 0){
                current = current.right;
            }
            else{
                return current.val;
            }
        }
        return null;
    }

    public void delete(K key){
        Node<K, V> parent = null;
        Node<K, V> current = root;
        while(current != null && !key.equals(current.key)){
            parent = current;
            if(key.compareTo(current.key) < 0){
                current = current.left;
            }else{
                current = current.right;
            }
        }

        if(current == null) return;

        if(current.left == null || current.right == null){
            Node child = (current.left != null) ? current.left : current.right;

            if (parent == null) {
                root = child;
            }else if(parent.left ==current){
                parent.left = child;
            }else {
                parent.right = child;
            }
        } else {
            Node<K, V> successorParent = current;
            Node<K, V> successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.key = successor.key;
            current.val = successor.val;

            if (successorParent.left == successor)
                successorParent.left = successor.right;
            else
                successorParent.right = successor.right;
        }

        size--;
    }

    public Iterator<Node<K, V>> iterator() {
        List<Node<K, V>> list = new ArrayList<>();
        Stack<Node<K, V>> stack = new Stack<>();
        Node<K, V> current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            list.add(current);
            current = current.right;
        }

        return list.iterator();
    }

}
