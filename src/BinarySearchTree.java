public class BinarySearchTree {

    // Node is a private class implemented for use in the SinglyLinkedList class
    private static class Node{

        private String word;
        private int freq;
        private Node rightNode;
        private Node leftNode;

        // Node constructor
        public Node(String word){
            this.word = word;
            this.rightNode = null;
            this.leftNode = null;
            this.freq = 1;
        }

        public String getWord(){
            return this.word;
        }

        public void addFrequency(){
            this.freq = this.freq + 1;
        }

        public int getFrequency(){
            return this.freq;
        }

        public void setLeftNode(Node newNode){
            this.leftNode = newNode;
        }

        public void setRightNode(Node newNode){
            this.rightNode = newNode;
        }

        public Node getLeftNode(){
            return this.leftNode;
        }

        public Node getRightNode(){
            return this.rightNode;
        }
    }

    private Node root;
    private int size = 0;
    private int height = 0;

    public void BinarySearchTree(){
        this.root = null;
    }

    public void addNode(String word){
        Node newNode = new Node(word);
        if (root == null) {
            root = newNode;
            this.size++;
            this.height++;
        }
        else {
            Node focusNode = root;
            Node temp = root;
            Node parent;

            while (true) {
                parent = focusNode;
                if (alphabetical(word.toLowerCase(), focusNode.getWord()) == 0){
                    focusNode.addFrequency();
                    return;
                }
                else if (alphabetical(word.toLowerCase(), focusNode.getWord()) == 1) {
                    temp = focusNode.getRightNode();
                    focusNode = focusNode.getLeftNode();
                    if (focusNode == null) {
                        parent.setLeftNode(newNode);
                        this.size++;
                        if (temp == null)
                            this.height++;
                        return;
                    }
                }
                else {
                    temp = focusNode.getLeftNode();
                    focusNode = focusNode.getRightNode();
                    if (focusNode == null){
                        parent.setRightNode(newNode);
                        this.size++;
                        if (temp == null)
                            this.height++;
                        return;
                    }
                }
            }
        }
    }

    public int getSize(){
        return this.size;
    }

    public Node getRoot(){
        return this.root;
    }

    public int getHeight(){
        return this.height;
    }

    public void traverseInOrder(Node focusNode, String[] arrWord, int[] arrFreq){
        if (focusNode != null){
            traverseInOrder(focusNode.getLeftNode(), arrWord, arrFreq);
            for (int z = 0; z < arrFreq.length; z++){
                if (arrFreq[z] < 1){
                    arrFreq[z] = focusNode.getFrequency();
                    arrWord[z] = focusNode.getWord();
                    break;
                }
            }
            traverseInOrder(focusNode.getRightNode(), arrWord, arrFreq);
        }
    }

    public void traversePreOrder(Node focusNode, String[] arrWord, int[] arrFreq) {
        if (focusNode != null) {
            for (int z = 0; z < arrFreq.length; z++){
                if (arrFreq[z] < 1){
                    arrFreq[z] = focusNode.getFrequency();
                    arrWord[z] = focusNode.getWord();
                    break;
                }
            }
            traversePreOrder(focusNode.getLeftNode(), arrWord, arrFreq);
            traversePreOrder(focusNode.getRightNode(), arrWord, arrFreq);
        }
    }

    public int traversePostOrder(Node focusNode, String[] arrWord, int[] arrFreq, int count, int ret) {
        if (focusNode != null) {
            count++;
            ret = traversePostOrder(focusNode.getLeftNode(), arrWord, arrFreq, count, ret);
            ret = traversePostOrder(focusNode.getRightNode(), arrWord, arrFreq, count, ret);
            for (int z = 0; z < arrFreq.length; z++){
                if (arrFreq[z] < 1){
                    arrFreq[z] = focusNode.getFrequency();
                    arrWord[z] = focusNode.getWord();
                    break;
                }
            }
        }
        if (count > ret)
            ret = count;
        return ret;
    }

    public int alphabetical(String s1, String s2){
        for (int z = 0; z < s1.length() && z < s2.length(); z++){
            if (s1.charAt(z) < s2.charAt(z))
                return 1;
            else if (s1.charAt(z) > s2.charAt(z))
                return 2;
        }
        if (s1.length() < s2.length())
            return 1;
        else if (s1.length() > s2.length())
            return 2;
        return 0;
    }
}
