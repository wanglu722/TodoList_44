import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 待办事项实体类
class TodoItem {
    private String content; // 待办内容
    private boolean isCompleted; // 完成状态

    // 构造方法
    public TodoItem(String content) {
        this.content = content;
        this.isCompleted = false; // 默认未完成
    }

    // getter/setter
    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

// 核心业务逻辑类
public class TodoList {
    private static List<TodoItem> todoList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // 显示功能菜单
    private static void showMenu() {
        System.out.println("\n===== 待办事项管理 =====");
        System.out.println("1. 添加待办事项");
        System.out.println("2. 查看所有待办");
        System.out.println("3. 标记待办为完成");
        System.out.println("4. 删除待办事项");
        System.out.println("5. 退出程序");
        System.out.println("========================");
    }

    // 添加待办
    private static void addTodo() {
        System.out.print("请输入待办事项内容：");
        String content = scanner.nextLine().trim();
        if (content.isEmpty()) {
            System.out.println("❌ 待办内容不能为空！");
            return;
        }
        todoList.add(new TodoItem(content));
        System.out.println("✅ 已添加待办：" + content);
    }

    // 查看所有待办
    private static void viewTodos() {
        if (todoList.isEmpty()) {
            System.out.println("📄 暂无待办事项～");
            return;
        }
        System.out.println("\n📋 你的待办列表：");
        for (int i = 0; i < todoList.size(); i++) {
            TodoItem item = todoList.get(i);
            String status = item.isCompleted() ? "✅ 已完成" : "🔴 未完成";
            System.out.println((i + 1) + ". " + status + " | " + item.getContent());
        }
    }

    // 标记待办为完成
    private static void markAsCompleted() {
        if (todoList.isEmpty()) {
            System.out.println("📄 暂无待办事项～");
            return;
        }
        viewTodos();
        System.out.print("请输入要标记完成的待办序号：");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < todoList.size()) {
                TodoItem item = todoList.get(index);
                item.setCompleted(true);
                System.out.println("✅ 已标记「" + item.getContent() + "」为完成！");
            } else {
                System.out.println("❌ 序号不存在！");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ 请输入有效的数字！");
        }
    }

    // 删除待办
    private static void deleteTodo() {
        if (todoList.isEmpty()) {
            System.out.println("📄 暂无待办事项～");
            return;
        }
        viewTodos();
        System.out.print("请输入要删除的待办序号：");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < todoList.size()) {
                TodoItem deletedItem = todoList.remove(index);
                System.out.println("🗑️ 已删除待办：" + deletedItem.getContent());
            } else {
                System.out.println("❌ 序号不存在！");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ 请输入有效的数字！");
        }
    }

    // 主方法（程序入口）
    public static void main(String[] args) {
        System.out.println("欢迎使用简单待办事项管理工具！");
        while (true) {
            showMenu();
            System.out.print("请输入操作编号（1-5）：");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addTodo();
                    break;
                case "2":
                    viewTodos();
                    break;
                case "3":
                    markAsCompleted();
                    break;
                case "4":
                    deleteTodo();
                    break;
                case "5":
                    System.out.println("👋 退出程序，再见！");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("❌ 输入错误，请输入1-5的数字！");
            }
        }
    }
}