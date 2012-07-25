package info1103;

public class ancestor {
    public static String getParent(String[] commitHashes, String[][] parentHashes, String commitHash) {
        int pos = 0;
        for (int i = 0; i < commitHashes.length; i++) {
            if (commitHash.equals(commitHashes[i])) {
                pos = i;
            }
        }
        if (parentHashes[pos]==null) {
        	return null;
        }
        return parentHashes[pos][0];
    }
    public static int getDepth(String[] commitHashes, String[][] parentHashes, String commitHash) {
    	int depth = 0;
    	if (getParent(commitHashes,parentHashes,commitHash) == null) {
    		return depth;
    	}
    	else {
    		return 1 + getDepth(commitHashes,parentHashes,getParent(commitHashes,parentHashes,commitHash));
    	}
    }
    public static String findCommonAncestor(String[] commitHashes, String[][] parentHashes, String commitHash1, String commitHash2) {
    	int depth1 = getDepth(commitHashes,parentHashes,commitHash1);
    	int depth2 = getDepth(commitHashes,parentHashes,commitHash2);
    	while (depth1 > depth2) {
    		commitHash1 = getParent(commitHashes,parentHashes,commitHash1);
    	}
    	while (depth2 > depth1) {
    		commitHash2 = getParent(commitHashes,parentHashes,commitHash2);
    	}
    	while (commitHash1 != commitHash2) {
    		commitHash1 = getParent(commitHashes,parentHashes,commitHash1);
    		commitHash2 = getParent(commitHashes,parentHashes,commitHash2);
    	}
    	return commitHash1;
    }
    public static void main(String[] args) {
    	String[] commits = {"G","F","E","D","C","B","A"};
    	String[][] parents = {{"F","D"},{"E"},{"B"},{"C"},{"B"},{"A"},null};
    	String commit1 = "D";
    	String commit2 = "F";
    	int depth = 0;
    	System.out.println(getParent(commits, parents, commit1));
    	System.out.println(getDepth(commits,parents,commit1));
    	System.out.println(findCommonAncestor(commits,parents,commit1,commit2));
    }
}
