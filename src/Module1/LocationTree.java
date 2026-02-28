package Module1;

// avl tree class
class LocationTree {

    private Location rootLocation;

    // Get the height of the Location
    private int height(Location location) {
        if (location == null)
            return 0;
        return location.height;
    }

    // Get maximum of two integers
    private int max(int location1Height, int location2Height) {
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
    private Location rightRotate(Location location) {
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
    private Location leftRotate(Location location) {
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
    private int getBalance(Location location) {
        if (location == null)
            return 0;
        return height(location.left) - height(location.right);
    }
    
    private Location rebalance(Location rootLocation) {
        // Update height of the current node
        rootLocation.height = 1 + Math.max(height(rootLocation.left), height(rootLocation.right));

        // Get balance factor
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

    
    // adding a new location to the location tree
    // we use this method as a wraper to the below insertHelper method to insert the root location of the tree
    // that allows the program to perform the recursion 
    public void insert(String location) {
        // insert to the new location to the toot tree direcly 
        // finally assigning the resulting tree to the root tree to refresh with all the new updates
        this.rootLocation = insertHelper(this.rootLocation, location);
    }
    Location insertHelper(Location rootLocation, String location) {
        // returning the new location to the previous recursive call's rootLocation
        if (rootLocation == null)
            return new Location(location);

        // other wise the program will compare all the encounting nodes with the incoming location name 
        // it travers to the end of the tree according to the comparison 
        if (rootLocation.name.compareTo(location) > 0) 
            rootLocation.left = insertHelper(rootLocation.left, location);
        else if (location.compareTo(rootLocation.name) > 0)
            rootLocation.right = insertHelper(rootLocation.right, location);
        else
            // fires if the location is already exist and simply returns the same location node to the perent
            return rootLocation;

        // simply pass the current root location
        // then returns the rebalanced tree to the main 
        return rebalance(rootLocation);

    }

    // removing location from the tree
    // because we have to give the root as default 
    public Location delete(String location) {
        this.rootLocation = deleteHelper(this.rootLocation, location);
        return this.rootLocation;
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

        // Rebalance the tree
        return rebalance(rootLocation);
    }

    // searching methods
    public Location search(String location){
        return searchHelper(this.rootLocation,location);
    }
    public Location searchHelper(Location rootLocation, String location) {
        if (rootLocation == null || rootLocation.name.equals(location)) {
            return rootLocation;
        }

        if (rootLocation.name.compareTo(location) > 0) {
            return searchHelper(rootLocation.left, location);
        }

        return searchHelper(rootLocation.right, location);
    }

    // breath first traversal using queue
    public void bft() {
        
    }
}