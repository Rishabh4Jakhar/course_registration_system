public abstract class User {
    String username; // ADMIN for Admin
    String password; // 0000 for Admin
    int permission; // 0 For Student, 1 For Professor, 2 For Admin
    abstract void get_info();
}
