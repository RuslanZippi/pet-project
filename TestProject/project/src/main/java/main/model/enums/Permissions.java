package main.model.enums;


public enum Permissions {
    USER("user:write"),
    MODERATOR("user:moderate");

    private final String permissions;

    Permissions(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
