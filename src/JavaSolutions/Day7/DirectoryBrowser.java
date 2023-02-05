package Day7;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DirectoryBrowser {
    public static void main(String[] args) throws IOException {
        Scanner terminal = new Scanner(new File("resources\\terminal.txt"));
        Tree home = new Tree("/", null);
        Tree parent = home;
        while (terminal.hasNextLine()) {
            String[] curr = terminal.nextLine().split(" ");
            if (curr[0].equals("$")) {
                switch (curr[1]) {
                    case "cd":
                        String name = curr[2];
                        if (curr[2].equals("/")) {
                            parent = home;
                            break;
                        }
                        if (curr[2].equals("..")) {
                            if (parent.parent == null)
                                throw new IOException("Attempted to traverse up from root");
                            parent = parent.parent;
                            break;
                        }
                        if (parent.children.containsKey(name)) parent = parent.children.get(name);
                        else parent = new Tree(name, parent);

                        break;
                    case "ls":
                        break;
                    default:
                        throw new IOException("KUR FILE");
                }
            } else if (curr[0].equals("dir")) {
                if (!parent.children.containsKey(curr[1])) parent.children.put(curr[1], new Tree(curr[1], parent));
            } else {
                parent.sum += (Integer.parseInt(curr[0]));
            }

        }
        int sum1 = 0;
        List<Tree> tree = full(home);
        List<Tree> sufficient = new ArrayList<>();
        int goal = 30000000;
        int total = 70000000;
        int used = home.getTotalSize();
        int free = total - used;

        for (Tree a : tree) {
            if (a.getTotalSize() <= 100000) sum1 += a.getTotalSize();
            if (a.getTotalSize() >= goal - free) sufficient.add(a);
        }
        sufficient.sort(new Comparator<Tree>() {
            @Override
            public int compare(Tree o1, Tree o2) {
                return (int) Math.signum(o1.getTotalSize()-o2.getTotalSize());
            }
        });

        System.out.println(sufficient.get(0).toString());
        System.out.println(sufficient.get(sufficient.size()-1).toString());
        System.out.println("Sum of all directories under 100000:" + sum1);
        System.out.println("Directory for deletion:" + sufficient.get(0).toString());

    }

    public static List<Tree> full(Tree root) {
        List<Tree> a = new ArrayList<>();
        a.add(root);
        for (Tree b : root.children.values()) a.addAll(full(b));
        return a;
    }
}

class Tree {
    Tree parent;
    String name;
    HashMap<String, Tree> children;
    int sum;


    public Tree(String name, Tree parent) {
        this.parent = parent;
        this.children = new HashMap<>();
        this.sum = 0;
        this.name = name;
    }

    public int getTotalSize() {
        int s = 0;
        for (Tree a : children.values()) {
            s += a.getTotalSize();
        }
        return sum + s;
    }

    public String toString() {return "Directory:" + name + "\nSize:" + getTotalSize();}


}


