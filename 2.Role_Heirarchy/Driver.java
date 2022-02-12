import java.util.*;

public class Driver {
    static Scanner sc ;
    static HierachicalTree hTree;
    public static void main(String[] args)  {
        System.out.println("Enter root role name: ");
        sc = new Scanner(System.in);
        String curRole  = sc.nextLine();
        Node root = new Node(curRole);
        System.out.println(root.role);
         hTree = new HierachicalTree(root);
        boolean isDone = false;
        while ( !isDone ){
            displayOps();
            int ch = Integer.parseInt(sc.nextLine());
            try {
                switch (ch){
                case 1:
                    addSubRole();
                    break;
                case 2:
                    displayRoles();
                    break;
                case 3:
                    deleteRole();
                    break;
                case 4:
                    addUser();
                    break;
                case 5:
                    displayUsers();
                    break;
                case 6:
                    displayUsersAndSubUsers();
                    break;
                case 7:
                    deleteUser();
                    break;
                case 8:
                    numberOfUsersFromTop();
                    break;
                case 9:
                    heightOfHierarchy();
                    break;
                case 10:
                    topMostcommonBossOfUsers();
                    break;
                case 11:
                    isDone = true;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option");
            }
            } catch(InvalidReportingRole ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void topMostcommonBossOfUsers() {
        System.out.println("Operation to be performed : 10");
        System.out.println("Enter user 1 : ");
        String usrname1 = sc.nextLine();
        Node u1 = hTree.findNodeByName(usrname1);
        System.out.println("Enter user 2 : ");
        String username2 = sc.nextLine();
        Node u2 = hTree.findNodeByName(username2);
        LinkedList<Node> l1 = computeList(u1);
        LinkedList<Node> l2 = computeList(u2);
        Node prev = null;
        while (!l1.isEmpty() && !l2.isEmpty()){
            Node cur1 = l1.poll();
            Node cur2 = l2.poll();
            if(cur1.equals(cur2)){
                prev = cur1;
            }else {
                break;
            }
        }
       if(prev!=null)
           System.out.println("Top most common boss : " + prev.name);
    }

    private static LinkedList<Node> computeList(Node u1) {
        LinkedList<Node> res = new LinkedList<>();
        res.add(u1);
        Node first = u1;
        while (u1.parent!=null){
            res.add(u1);
            u1 = u1.parent;
        }
        if(!first.equals(u1))
            res.add(u1);
        Collections.reverse(res);
        return res;
    }

    private static void heightOfHierarchy() {
        Queue<Node> queue = new LinkedList<>();
        int height = 0;
        queue.add(hTree.getRoot());
        Set<Node> visited = new HashSet<>();
        visited.add(hTree.getRoot());
        queue.add(null);
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            if(curNode == null) {
                height++;
               if(!queue.isEmpty())
                   queue.add(null);
                continue;
            }
            List<Node> childs = curNode.subs;
            for(Node child :childs){
                if(!visited.contains(child)){
                    queue.add(child);
                    visited.add(curNode);
                }
            }
        }
        System.out.println("height-" + height);
    }

    private static void numberOfUsersFromTop() {
        System.out.println("Operation to be performed : 8 ");
        System.out.println("Enter user name : ");
        String userName = sc.nextLine();
        Queue<Node> queue = new LinkedList<>();
        int height = 1;
        queue.add(hTree.getRoot());
        Set<Node> visited = new HashSet<>();
        visited.add(hTree.getRoot());
        queue.add(null);
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            if(curNode == null) {
                height++;
                queue.add(null);
                continue;
            }
            if (curNode.name!=null && curNode.name.equals(userName)){
                break;
            }
            List<Node> childs = curNode.subs;
            for(Node child :childs){
                if(!visited.contains(child)){
                    queue.add(child);
                    visited.add(curNode);
                }
            }
        }
        System.out.println("Number of users from top : "+ height);
    }

    private static void deleteUser() {
        System.out.println("Operation to be performed : 7");
        System.out.println("Enter username to be deleted :");
//        sc.nextLine();
        String uname = sc.nextLine();
        Node cur = hTree.findNodeByName(uname);
        cur.name = null;
//        displayUsers();
//        deleteRole();
        System.out.println("Operation to be performed : 3");
        System.out.println("Enter the role to be deleted : ");
        String dRole = sc.nextLine();
        Node dNode = hTree.findNodeByRole(dRole);
        System.out.println("Enter the role to be transferred :");
        String tRole = sc.nextLine();
        Node tNode = hTree.findNodeByRole(tRole);
        if(tNode == null)
            tNode = new Node(tRole);
        tNode.name = uname;
        hTree.addSubordinate(tNode, hTree.findNodeByRole(hTree.roleMap.get(tRole)));

    }

    private static List<String> displaySubUsers(Node user){

        List<String> res = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(user);
        Set<Node> visited = new HashSet<>();
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            visited.add(curNode);
            if (curNode.name != null && !curNode.equals(user))
                res.add(curNode.name);
            List<Node> childs = curNode.subs;
            for(Node child :childs){
                if(!visited.contains(child))
                    queue.add(child);
            }
        }
        return res;
    }
    private static void displayUsersAndSubUsers() {
       System.out.println( "Operation to be performed : 6" );
        Queue<Node> queue = new LinkedList<>();
        queue.add(hTree.getRoot());
        Set<Node> visited = new HashSet<>();
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            visited.add(curNode);
            if (curNode.name != null ){
                System.out.print(curNode.name + " - ");
                for (String name : displaySubUsers(curNode)){
                       System.out.print(name+ ", ");
                }
                System.out.println();
            }
            List<Node> childs = curNode.subs;
            for(Node child :childs){
                if(!visited.contains(child))
                    queue.add(child);
            }
        }
    }

    private static void displayUsers() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(hTree.getRoot());
        Set<Node> visited = new HashSet<>();
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            visited.add(curNode);
            if (curNode.name != null )
                System.out.println(curNode.name +" - " +  curNode.role );
            List<Node> childs = curNode.subs;
            for(Node child :childs){
                if(!visited.contains(child))
                    queue.add(child);
            }
        }
    }

    private static void addUser() {
       System.out.println( "Operation to be performed : 4");
       System.out.println( "Enter User Name :  ");
//        sc.nextLine();
       String name = sc.nextLine();
       System.out.println( "Enter Role : " );
       String role = sc.nextLine();
       Node curRole = hTree.findNodeForUser(role);
       curRole.name = name;
    }

    private static void deleteRole() {
        System.out.println("Operation to be performed : 3");
//        sc.nextLine();
        System.out.println("Enter the role to be deleted : ");
        String dRole = sc.nextLine();
        Node dNode = hTree.findNodeByRole(dRole);
        System.out.println("Enter the role to be transferred :");
        String tRole = sc.nextLine();
        Node tNode = hTree.findNodeByRole(tRole);
        if(tNode == null)
            tNode = new Node(tRole);
        hTree.deleteAndAttach(dNode, tNode);
    }

    private static void displayRoles() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(hTree.getRoot());
        Set<Node> visited = new HashSet<>();
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            visited.add(curNode);
            System.out.print(curNode.role +", ");
            List<Node> childs = curNode.subs;
            for(Node child :childs){
             if(!visited.contains(child))
                 queue.add(child);
            }
        }
        System.out.println();
    }

    private static void addSubRole() throws InvalidReportingRole {
        System.out.println("Operation to be performed : 1");
        System.out.println("Enter sub role name :");
//        sc.nextLine();
        String curRole = sc.nextLine();
        System.out.println("Enter reporting role name : ");
        String parentRole = sc.nextLine();
        Node parentNode = hTree.findNodeByRole(parentRole);
        if (parentNode.role.equals("DUMMY"))
            throw new InvalidReportingRole("The reporting role is not found in hierarchical tree");
        Node curNode = new Node(curRole);
        hTree.addSubordinate(curNode, parentNode);
        displayRoles();

    }

    private static void displayOps() {
        System.out.println("Operations :");
        System.out.println(" 1. Add Sub Role.");
        System.out.println(" 2. Display Roles");
        System.out.println(" 3. Delete Role.");
        System.out.println(" 4. Add User.");
        System.out.println(" 5. Display Users.");
        System.out.println(" 6. Display Users and Sub Users.");
        System.out.println(" 7. Delete User.");
        System.out.println(" 8. Number of users from top");
        System.out.println(" 9. Height of role hierachy.");
        System.out.println(" 10. Common boss of users");
        System.out.println(" 11. Exit");

    }
}

class HierachicalTree {
private Node root;
public Map<String, String> roleMap;
    public HierachicalTree(Node root) {
        this.root = root;
        this.roleMap = new HashMap<>();
        initRoleMap();
    }

    private void initRoleMap() {
        roleMap.put("COO", "CEO");
        roleMap.put("CTO", "CEO");
        roleMap.put("Sr Product Eng Manager", "COO");
        roleMap.put("Sr Product Marketing Manager", "COO");
        roleMap.put("Director of Technology", "CTO");
        roleMap.put("Technical Architect", "Director of Technology");
        roleMap.put("Manager Engineering", "Sr Product Eng Manager");
        roleMap.put("DevOps", "Manager Engineering");
        roleMap.put("Developer", "Manager Engineering");
        roleMap.put("Tester", "Manager Engineering");
        roleMap.put("Manager Marketing", "Sr Product Marketing Manager");
        roleMap.put("Marketing Analyst", "Manager Marketing");
        roleMap.put("Marketing Executive", "Manager Marketing");

    }

    public Node findNodeByName(String targetUname) {
        if(root.name == null)
            return new Node("DUMMY");
        if(root.name.equals(targetUname))
            return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Set<Node> visited = new HashSet<>();
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            visited.add(curNode);
            if(curNode.name!=null && curNode.name.equals(targetUname)){
                return curNode;
            }

            List<Node> childs = curNode.subs;
            for(Node child :childs){
                if(!visited.contains(child))
                    queue.add(child);
            }
        }
        return new Node("DUMMY");
    }

    public void addSubordinate(Node curNode, Node parentNode) {
        parentNode.subs.add(curNode);
        curNode.parent = parentNode;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node findNodeForUser(String target) {
        if(root.role.equals(target))
            return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Set<Node> visited = new HashSet<>();
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            visited.add(curNode);
            if(curNode.role.equals(target)){
                if (curNode.name != null){
                   Node res =  new Node(target);
                   res.parent = curNode.parent;
                   curNode.parent.subs.add(res);
                   return res;
                }
                return curNode;
            }

            List<Node> childs = curNode.subs;
            for(Node child :childs){
                if(!visited.contains(child))
                    queue.add(child);
            }
        }
        return new Node("DUMMY");
    }
    public Node findNodeByRole(String target) {
        if(root.role.equals(target))
            return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Set<Node> visited = new HashSet<>();
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            visited.add(curNode);
            if(curNode.role.equals(target))
                return curNode;
            List<Node> childs = curNode.subs;
            for(Node child :childs){
                if(!visited.contains(child))
                    queue.add(child);
            }
        }
        return null;
    }

    public void deleteAndAttach(Node dNode, Node tNode) {
        tNode.subs.addAll(dNode.subs);
        dNode.parent.subs.remove(dNode);
        dNode.parent.subs.add(tNode);
        dNode.parent = null;
        dNode.subs = null;
    }

}


class Node{
    String name;
    String role;
    Node parent;
    List<Node> subs;

    public Node(String role) {
        this.role = role;
        this.subs = new ArrayList<>();
    }
}
class InvalidReportingRole extends Exception{
    public InvalidReportingRole(String msg) {
        super(msg);
    }
}