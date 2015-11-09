package namoo.security.v3.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import namoo.security.v3.domain.authentication.entity.PMSRole;
import namoo.security.v3.domain.authentication.service.RoleService;
import namoo.security.v3.web.security.UnitRoleContext;
import namoo.security.v3.z_library.StringUtil;

@Controller
public class DefaultPageController {
    
    private static final String SYSTEM_DEFAULT_URI = "/login";
    
    @Autowired
    private RoleService roleService;
    
    /**
     * 로그인 후 연결되는 기본 서블릿.
     * : 사용자별 기본페이지 URL을 연결한다.
     * 
     * @return 사용자 역할별 기본페이지
     */
    @RequestMapping("/default")
    public String selectPage() {
        // 
        return "redirect:" + getDefaultPage();
    }
    
    /**
     * 현재 UnitRoleContext에 설정된 사용자의 역할을 기반으로 기본 페이지 정보를 제공한다.
     * 
     * @return
     */
    private String getDefaultPage(){
        //
        PMSRole majorRole = roleService.inquireRole(UnitRoleContext.getMajorRoleId());
        String defaultPageURI = majorRole.getDefaultPageURI();
        
        if (StringUtil.isNullOrNullString(defaultPageURI)) {
            return SYSTEM_DEFAULT_URI;
        }
        
        return defaultPageURI;
    }
    
    /**
     * <br>
     * 단, 메뉴정보가 전혀 없는경우 본 리소스에서 정의한 시스템 기본 URI를 제공한다.
     * TODO 일단은 적용안하고 해보자.. 
     * @return
     */
//    private String getDefaultPageFormMenu(){
//        PMSMenu rootMenu = MenuBean.getMenus(context);
//        String defaultPage = null;
//        // HOME을 제외한 메뉴에서 첫번째 메뉴를 선택한다.
//        if(!rootMenu.getSubResources().isEmpty() && rootMenu.getSubResources().size() > 1){
//            defaultPage = rootMenu.getSubResources().get(1).getAdjustedUri();
//        }else{// HOME 메뉴만 있는경우 HOME을 선택한다.
//            defaultPage = rootMenu.getSubResources().get(0).getAdjustedUri();
//        }
//        if(!unableURI(defaultPage))
//            return defaultPage;
//        return SYSTEM_DEFAULT_URI;
//    }

}
