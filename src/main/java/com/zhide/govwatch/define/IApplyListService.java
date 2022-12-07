package com.zhide.govwatch.define;

        import com.zhide.govwatch.common.pageObject;
        import com.zhide.govwatch.model.ComboboxItem;

        import javax.servlet.http.HttpServletRequest;
        import java.util.List;
        import java.util.Map;

public interface IApplyListService {

    pageObject clientFYJK(Map<String, Object> parameters);

    Map<String, Object> getParameters(HttpServletRequest request) throws Exception;
}
