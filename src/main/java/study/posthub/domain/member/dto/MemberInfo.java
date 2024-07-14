package study.posthub.domain.member.dto;

public class MemberInfo {
    private String name;
    private String authority;
    private boolean isAuthenticated;

    public MemberInfo(String name, String authority, boolean isAuthenticated) {
        this.name = name;
        this.authority = authority;
        this.isAuthenticated = isAuthenticated;
    }

    public String getName() {
        return name;
    }

    public String getAuthority() {
        return authority;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}
