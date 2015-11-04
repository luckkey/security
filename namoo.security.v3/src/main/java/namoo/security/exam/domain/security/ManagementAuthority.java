
package namoo.security.exam.domain.security;

import java.io.Serializable;

/**
 * 권한관리용 BDT 클래스이다. PMIS에서 사용하는 각종 자원에 대한 접근권한을 표현한다. 여기에는 자원에 대한 생성, 삭제, 조회, 갱신 권한등이 포함된다.
 * 
 * 권한관리용 BDT
 * 
 * @author Bee
 * @since 2009. 07. 02
 */
public class ManagementAuthority implements Serializable {

    private static final long serialVersionUID = 6746511515650941163L;

    public static final String YES = "Y";
    public static final String NO = "N";

    /** 역할정보 */
    private PMSRole role;

    /** 자원정보 */
    private ManagementMenu resource;

    /** 조회권한 */
    private String readYN = NO;
    private String writeYN = NO;
    private String useYN = YES;
    // private String readYN = CommonConstants.NO;
    // private String writeYN = CommonConstants.NO;
    // private String useYN = CommonConstants.YES;

    /**
     * 권한 객체를 생성하기 위한 기본 생성자이다.
     */
    public ManagementAuthority() {

    }

    /**
     * 기본정보 모두를 권한값을 가지고 시작할수 있는 생성자
     */
    public ManagementAuthority(boolean hasDefaultValue) {
        if (hasDefaultValue)
            return;
        writeYN = null;
        readYN = null;
        // 기능권한 제외
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
        return checkAuthority(PMSAuthority.AUTH_READ, authType, getReadYN())
                || checkAuthority(PMSAuthority.AUTH_WRITE, authType, getWriteYN());
    }

    private boolean checkAuthority(String target, String source, String yn) {
        return target.equals(source) && YES.equals(yn);
    }

    public ManagementAuthority(String roleId, Long resourceId) {
        setRoleId(roleId);
        setResourceId(resourceId);
    }

    /**
     * PMS Authority를 이용해서 기본권한 정보를 모두 생성한다.
     * 
     * @param pmsAuthority
     */
    public ManagementAuthority(PMSAuthority pmsAuthority) {
        setRoleId(pmsAuthority.getRoleId());
        this.readYN = pmsAuthority.getReadYN();
        this.writeYN = pmsAuthority.getWriteYN();

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
            this.resource = new ManagementMenu();
        }

        this.resource.setResourceId(resourceId);
    }

    public Long getResourceId() {
        return this.resource == null ? null : this.resource.getResourceId();
    }

    public String getUrn() {
        return this.resource == null ? null : this.resource.getUrn();
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

    public ManagementMenu getResource() {
        return resource;
    }

    public void setResource(ManagementMenu resource) {
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

    /**
     * @return the useYN
     */
    public String getUseYN() {
        return useYN;
    }

    /**
     * @param useYN
     *            the useYN to set
     */
    public void setUseYN(String useYN) {
        this.useYN = useYN;
    }

}
