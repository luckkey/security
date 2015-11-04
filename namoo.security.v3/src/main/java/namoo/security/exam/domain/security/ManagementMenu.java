package namoo.security.exam.domain.security;

/**
 * 관리대상에서 변경된 메뉴의 정보
 *
 * @author <a href="mailto:sgyoum@nextree.co.kr">sgyoum</a>
 * @since 2012. 5. 22.
 */
public class ManagementMenu extends PMSResource {

	/**	UID	*/
	private static final long serialVersionUID = -2291625583271169697L;

	/** 아이디 */
	private Long id;
	
	/** 관리대상 아이디 */
	private Long muId;
	
	/** 사용여부 */
	private String useYn = "Y";
	
	/** 사용여부 */
	private String authorityUseYn = "Y";
	
	/** 자원 순서 */
    private int sequence;
    
    /** 상위 자원 아이디 */
    private Long parentResourceId;

    public ManagementMenu() {		
	}
    
	public ManagementMenu(Long id) {
		this.id = id;
	}

	public ManagementMenu(Long resourceId, String urn) {
		setResourceId(resourceId);
		setUrn(urn);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMuId() {
		return muId;
	}

	public void setMuId(Long muId) {
		this.muId = muId;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Long getParentResourceId() {
		return parentResourceId;
	}

	public void setParentResourceId(Long parentResourceId) {
		this.parentResourceId = parentResourceId;
	}

	/**
	 * @return the authorityUseYn
	 */
	public String getAuthorityUseYn() {
		return authorityUseYn;
	}

	/**
	 * @param authorityUseYn the authorityUseYn to set
	 */
	public void setAuthorityUseYn(String authorityUseYn) {
		this.authorityUseYn = authorityUseYn;
	}
}
