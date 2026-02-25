package Module1;

// avl tree class
class LocationTree {

    Location rootLocation;

    // Get the height of the Location
    int height(Location location) {
        if (location == null)
            return 0;
        return location.height;
    }

    // Get maximum of two integers
    int max(int location1Height, int location2Height) {
        return (location1Height > location2Height) ? location1Height : location2Height;
    }

    private Location min(Location location) {
        Location current = location;

        // loop down to find the leftmost leaf
        while (current.left != null)
            current = current.left;

        return current;
    }

    // Right rotate subtree rootLocationed with Location
    Location rightRotate(Location location) {
        Location leftChild = location.left;
        Location temp = leftChild.right;
         
        // Perform rotation
        leftChild.right = location;
        location.left = temp;

        // Update heights
        location.height = max(height(location.left), height(location.right)) + 1;
        leftChild.height = max(height(leftChild.left), height(leftChild.right)) + 1;

        // Return new rootLocation
        return leftChild;
    }

    // Left rotate subtree rootLocationed with Location
    Location leftRotate(Location location) {
        Location rightChild = location.right;
        Location temp = rightChild.left;

        // Perform rotation
        rightChild.left = location;
        location.right = temp;

        // Update heights
        location.height = max(height(location.left), height(location.right)) + 1;
        rightChild.height = max(height(rightChild.left), height(rightChild.right)) + 1;

        // Return new rootLocation
        return rightChild;
    }

    // Get balance factor of Location
    int getBalance(Location location) {
        if (location == null)
            return 0;
        return height(location.left) - height(location.right);
    }

    // Insert a key into the AVL tree and return the new rootLocation of the subtree
    public void insert(String location) {
        this.rootLocation = insertHelper(this.rootLocation, location);
    }
    Location insertHelper(Location rootLocation, String location) {
        if (rootLocation == null)
            return new Location(location);

        if (rootLocation.name.compareTo(location) > 0) 
            rootLocation.left = insertHelper(rootLocation.left, location);
        else if (location.compareTo(rootLocation.name) > 0)
            rootLocation.right = insertHelper(rootLocation.right, location);
        else
            return rootLocation;

        // Update height of rootLocation
        rootLocation.height = 1 + max(height(rootLocation.left), height(rootLocation.right));

        // Get balance factor
        int balance = getBalance(rootLocation);

        // Left Left Case
        if (balance > 1 && rootLocation.left.name.compareTo(location) > 0)
            
            return rightRotate(rootLocation);

        // Right Right Case
        if (balance < -1 && location.compareTo(rootLocation.right.name) > 0) 
            return leftRotate(rootLocation);

        // Left Right Case
        if (balance > 1 && location.compareTo(rootLocation.left.name) > 0) {   
            rootLocation.left = leftRotate(rootLocation.left);
            return rightRotate(rootLocation);
        }

        // Right Left Case
        if (balance < -1 && rootLocation.right.name.compareTo(location) > 0) { 
            rootLocation.right = rightRotate(rootLocation.right);
            return leftRotate(rootLocation);
        }

        return rootLocation;
    }

    // removing location from the tree
    // because we have to give the root as default 
    public Location delete(String location) {
        return deleteHelper(this.rootLocation, location);
    }
    Location deleteHelper(Location rootLocation, String location) {
        if (rootLocation == null)
            return rootLocation;

        // If the key to be deleted is smaller than the rootLocation's key, then it lies in left subtree
        if (rootLocation.name.compareTo(location) > 0) 
            rootLocation.left = deleteHelper(rootLocation.left, location);

        // If the key to be deleted is greater than the rootLocation's key, then it lies in right subtree
        else if (location.compareTo(rootLocation.name) > 0) 
            rootLocation.right = deleteHelper(rootLocation.right, location);

        // if key is same as rootLocation's key, then this is the node to be deleted
        else {
            // node with only one child or no child
            if ((rootLocation.left == null) || (rootLocation.right == null)) {
                Location temp = rootLocation.left != null ? rootLocation.left : rootLocation.right;
                return temp;

            } else {
                // node with two children: Get the inorder successor (smallest in the right subtree)
                Location successor = min(rootLocation.right);

                // Copy the inorder successor's data to this node
                rootLocation.name = successor.name;

                // Delete the inorder successor
                rootLocation.right = deleteHelper(rootLocation.right, successor.name);
            }
        }

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        rootLocation.height = 1 + Math.max(height(rootLocation.left), height(rootLocation.right));

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether this node became unbalanced)
        int balance = getBalance(rootLocation);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(rootLocation.left) >= 0)
            return rightRotate(rootLocation);

        // Left Right Case
        if (balance > 1 && getBalance(rootLocation.left) < 0) {
            rootLocation.left = leftRotate(rootLocation.left);
            return rightRotate(rootLocation);
        }

        // Right Right Case
        if (balance < -1 && getBalance(rootLocation.right) <= 0)
            return leftRotate(rootLocation);

        // Right Left Case
        if (balance < -1 && getBalance(rootLocation.right) > 0) {
            rootLocation.right = rightRotate(rootLocation.right);
            return leftRotate(rootLocation);
        }

        return rootLocation;
    }

    // searchig
    public Location search(String location){
        return searchHelper(this.rootLocation,location);
    }
    // internal processing
    public Location searchHelper(Location rootLocation, String location) {
        if (rootLocation == null || rootLocation.name.equals(location)) {
            return rootLocation;
        }

        if (rootLocation.name.compareTo(location) > 0) {
            return searchHelper(rootLocation.left, location);
        }

        return searchHelper(rootLocation.right, location);
    }


    // Utility functions for traversal
    void preOrder(Location location) {
        if (location != null) {
            System.out.print(location.name + " ");
            preOrder(location.left);
            preOrder(location.right);
        }
    }

    void inOrder(Location location) {
        if (location != null) {
            inOrder(location.left);
            System.out.print(location.name + " ");
            inOrder(location.right);
        }
    }

    void postOrder(Location location) {
        if (location != null) {
            postOrder(location.left);
            postOrder(location.right);
            System.out.print(location.name + " ");
        }
    }

}