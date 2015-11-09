
package namoo.security.v3.domain.authentication.entity;

import java.io.Serializable;

/**
 * 권한관리용 BDT 클래스이다. PMIS에서 사용하는 각종 자원에 대한 접근권한을 표현한다. 여기에는 자원에 대한 생성, 삭제, 조회, 갱신 권한등이 포함된다.
 * 
 * 권한관리용 BDT
 * 
 * @author Bee
 * @since 2009. 07. 02
 */
public class PMSAuthority implements Serializable {

    private static final long serialVersionUID = 6746511515650941163L;

    public static final String AUTH_READ = "R";
    public static final String AUTH_WRITE = "W";
    public static final String AUTH_F1 = "F1";
    public static final String AUTH_F2 = "F2";
    public static final String AUTH_F3 = "F3";
    public static final String AUTH_F4 = "F4";
    public static final String AUTH_F5 = "F5";
    public static final String AUTH_F6 = "F6";
    public static final String AUTH_F7 = "F7";
    public static final String AUTH_F8 = "F8";
    public static final String AUTH_F9 = "F9";
    public static final String AUTH_F10 = "F10";

    public static final String YES = "Y";
    public static final String NO = "N";

    /** 역할정보 */
    private PMSRole role;

    /** 자원정보 */
    private PMSResource resource;

    /** 조회권한 */
    private String readYN = NO;

    /** 쓰기권한 */
    private String writeYN = NO;

    /**
     * 권한 객체를 생성하기 위한 기본 생성자이다.
     */
    public PMSAuthority() {

    }

    /**
     * 기본 값을 권한값을 가지고 시작할수 있는 생성자
     */
    public PMSAuthority(boolean hasDefaultValue) {
        if (hasDefaultValue) return;
        writeYN = null;
        readYN = null;
        // FIXME 기능권한 제외 
        // f1YN = null;
        // f2YN = null;
        // f3YN = null;
        // f4YN = null;
        // f5YN = null;
        // f6YN = null;
        // f7YN = null;
        // f8YN = null;
        // f9YN = null;
        // f10YN = null;
    }

    /**
     * 아규먼트로 넘어온 상태에 대한 권한이 있는지 여부를 반환한다.
     * 
     * @param authType
     *            검사할 권한 값
     * @return 권한이 있으면 true, 없으면 false
     */
    public boolean isAuthorized(String authType) {
        return checkAuthority(AUTH_READ, authType, getReadYN()) || checkAuthority(AUTH_WRITE, authType, getWriteYN());
    }

    private boolean checkAuthority(String target, String source, String yn) {
        return target.equals(source) && YES.equals(yn);
    }

    public PMSAuthority(String roleId, Long resourceId) {
        setRoleId(roleId);
        setResourceId(resourceId);
    }

    public void setRoleId(String roleId) {
        if (role == null) {
            role = new PMSRole();
        }

        role.setId(roleId);
    }

    public String getRoleId() {
        return role == null ? null : role.getId();
    }

    public void setResourceId(Long resourceId) {
        if (resource == null) {
            this.resource = new PMSResource();
        }

        this.resource.setResourceId(resourceId);
    }

    public Long getResourceId() {
        return this.resource == null ? null : this.resource.getResourceId();
    }

    /**
     * @return role
     */
    public PMSRole getRole() {
        return role;
    }

    /**
     * @param role
     *            설정할 role
     */
    public void setRole(PMSRole role) {
        this.role = role;
    }

    /**
     * @return resource
     */
    public PMSResource getResource() {
        return resource;
    }

    /**
     * @param resource
     *            설정할 resource
     */
    public void setResource(PMSResource resource) {
        this.resource = resource;
    }

    /**
     * @return readYN
     */
    public String getReadYN() {
        return readYN;
    }

    /**
     * @param readYN
     *            설정할 readYN
     */
    public void setReadYN(String readYN) {
        this.readYN = readYN;
    }

    public void enableAllPermission() {
        this.readYN = YES;
        this.writeYN = YES;
    }

    public String getWriteYN() {
        return writeYN;
    }

    public void setWriteYN(String writeYN) {
        this.writeYN = writeYN;
    }

}
