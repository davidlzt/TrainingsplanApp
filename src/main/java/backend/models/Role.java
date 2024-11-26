package backend.models;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    MODERATOR("moderator");

    private String rolename;

    Role(String rolename){
        this.rolename = rolename;
    }

    public String getRolename() {return rolename;}
}
