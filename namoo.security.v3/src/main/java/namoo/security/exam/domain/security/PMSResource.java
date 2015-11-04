
package namoo.security.exam.domain.security;

import java.io.Serializable;

/**
 * PMS의 각종 자원(메뉴, 산출물등)을 표현하기 위한 클래스이다.
 * FIXME 삭제여부
 * @author Bee
 * @since 2009. 06. 30
 */
public class PMSResource implements Serializable {

    private static final long serialVersionUID = 6071359326522006278L;

    private boolean immutable = false;
    
    /** 자원 - 메뉴 */
    public static final String RES_MENU = "RES_MENU";

    /** 자원 - 세부화면 */
    public static final String RES_SCREEN = "RES_SCREEN";
    
    /** 자원 - 동적메뉴 */
    public static final String RES_DYNA_MENU = "RES_DYNA_MENU";

    /** 자원 아이디 */
    private Long resourceId;
    
    /** 자원의 대상유형 (PJ,PG,PD,NO)*/
    private String type;

    /** 자원 종류 (메뉴, 파일, 기능) */
    private String kind;

    /** 자원명 */
    private String name;

    /** 접근 URI */
    private String uri;

    /** 자원설명 */
    private String note;

    /** 자원 식별명 (unique resource name) */
    private String urn;

    /** 각 메뉴에 할당된 권한 */
    private PMSAuthority authority;
    
    public PMSResource() {
    }

    public PMSResource(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * @return resourceId
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId
     *            설정할 resourceId
     */
    public void setResourceId(Long resourceId) {
    	isMutable();
        this.resourceId = resourceId;
    }

    /**
     * @return kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind
     *            설정할 kind
     */
    public void setKind(String kind) {
    	isMutable();
        this.kind = kind;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            설정할 name
     */
    public void setName(String name) {
    	isMutable();
        this.name = name;
    }

    /**
     * @return uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri
     *            설정할 uri
     */
    public void setUri(String uri) {
    	isMutable();
        this.uri = uri;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note == null ? "" : note ;
    }

    /**
     * @param note
     *            설정할 note
     */
    public void setNote(String note) {
    	isMutable();
        this.note = note;
    }

    /**
     * @return urn
     */
    public String getUrn() {
        return urn;
    }

    /**
     * @param urn
     *            설정할 urn
     */
    public void setUrn(String urn) {
    	isMutable();
        this.urn = urn;
    }

    /**
     * @return authority
     */
    public PMSAuthority getAuthority() {
        return authority;
    }

    /**
     * @param authority
     *            설정할 authority
     */
    public void setAuthority(PMSAuthority authority) {
    	isMutable();
        this.authority = authority;
    }

    /**
     * 해당 자원이 화면 자원인지 여부를 반환한다.
     * @return 화면이면 true, 아니면 false
     */
    public boolean isScreen() {
        if (this.kind == null) {
            return false;
        }
        return (this.kind.equals(PMSResource.RES_SCREEN));
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		isMutable();
		this.type = type;
	}
	
	public void immutable(){
		immutable = true;
	}
	
	/**
	 * 변경 가능 여부 체크
	 */
	protected void isMutable(){
		if(immutable) throw new IllegalStateException("메뉴의 속성을 변경할 수 없습니다.");
	}

}
