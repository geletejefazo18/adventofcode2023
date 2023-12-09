package challenge;

import java.util.List;

public class Day9 {

    public void getResult1(List<String> input) {
        int totalVal = 0;
        for(String string :input) {
            String [] arraystring = string.split("\\s+");
            int[] array = new int[arraystring.length];
            for(int i=0;i<arraystring.length;i++) {
                array[i] = Integer.parseInt(arraystring[i]);
            }
            Node node = null;
            boolean foundAllEqual = false;
            int[] toIterate = array;
            while(!foundAllEqual) {
                foundAllEqual = true;
                int val= 0;
                int[] newArray = new int[toIterate.length-1];
                for(int j=1;j<toIterate.length;j++) {
                    newArray[j-1] = toIterate[j] - toIterate[j-1];
                    if(j>1 && val != newArray[j-1]) {
                        foundAllEqual = false;
                    }
                    val = newArray[j-1];
                }
                if(node == null) {
                    node = new Node(newArray[newArray.length-1]);
                    node.setRight(new Node(toIterate[toIterate.length-1]));
                } else {
                    Node newNode = new Node(newArray[newArray.length-1]);
                    newNode.setRight(node);
                    node = newNode;
                }
                toIterate = newArray;
            }
            Node initNode = new Node(0);
            initNode.setLeft(node);
            while(initNode.getLeft().getRight() !=null ){
                Node nodeRight = new Node(initNode.getValue() + initNode.getLeft().getValue());
                nodeRight.setLeft(initNode.getLeft().getRight());
                initNode = nodeRight;
            }
            totalVal = totalVal + initNode.getValue() + initNode.getLeft().getValue();

        }
        System.out.println("Total val " +totalVal);

    }

    public void getResult2(List<String> input) {
        int totalVal = 0;
        for(String string :input) {
            String [] arraystring = string.split("\\s+");
            int[] array = new int[arraystring.length];
            for(int i=0;i<arraystring.length;i++) {
                array[i] = Integer.parseInt(arraystring[i]);
            }
            Node node = null;
            boolean foundAllEqual = false;
            int[] toIterate = array;
            while(!foundAllEqual) {
                foundAllEqual = true;
                int val= 0;
                int[] newArray = new int[toIterate.length-1];
                for(int j=1;j<toIterate.length;j++) {
                    newArray[j-1] = toIterate[j] - toIterate[j-1];
                    if(j>1 && val != newArray[j-1]) {
                        foundAllEqual = false;
                    }
                    val = newArray[j-1];
                }
                if(node == null) {
                    node = new Node(newArray[0]);
                    node.setLeft(new Node(toIterate[0]));
                } else {
                    Node newNode = new Node(newArray[0]);
                    newNode.setLeft(node);
                    node = newNode;
                }
                toIterate = newArray;
            }
            Node initNode = new Node(0);
            initNode.setRight(node);
            while(initNode.getRight().getLeft() !=null ){
                Node nodeLeft = new Node(initNode.getRight().getValue() - initNode.getValue());
                nodeLeft.setRight(initNode.getRight().getLeft());
                initNode = nodeLeft;
            }
            totalVal = totalVal + initNode.getRight().getValue() - initNode.getValue();

        }
        System.out.println("Total val " +totalVal);

    }

    public class Node  {

        public Node(int value) {
            this.value = value;
        }
        private int value;
        private Node left;
        private Node right;

        public void setRight(Node right) {
            this.right = right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getLeft() {
            return this.left;
        }

        public Node getRight() {
            return this.right;
        }

        public int getValue() {
            return this.value;
        }
    }
}
