<#list datas as data>
    <dl class="bounceIn" >
        <dt>
            <a href="#" target="_blank">
                <img src="/js/paging/img/tepage.gif">
            </a>
        </dt>
        <a href="#" target="_blank" class="title"></a>
        <p class="author">
<#--            <input type="checkbox" class="patentSelector" tag="${data.shenqingh}" id="${data.shenqingh}">-->
            <span class="typewg">[${data.shenqinglx!}]</span>
            <span onclick="windowtz('${data.shenqingh!}')" class="baythspan">${data.famingmc!}</span>
            <span class="bhspan">- ${data.shenqingh!}</span>
            <span class="typeanniu" style="">${data.lawstatus!}-${data.seclawstatus!}</span>
            <span class="typeanniu1" style="background: #dcae2e;color: white;padding: 5px;" id="${data.shenqingh}sta">${data.jkstatus!}</span>
        </p>
        <p class="publisher">
            申请人:${data.shenqingrxm!}; - 申请日:${(data.shenqingr?string["yyyy.MM.dd"])!} - 主分类号:${data.pipc!}
        </p>
        <p class="daili">代理机构:${data.dailijgdm!} ${data.dailijgmc!} - 代理人:${data.dailirxm!}</p>
        <p class="daili">
            摘要:${(data.memo!)}
        </p>
        <p class="listxxitem"><span><a href="" target="_blank">PDF全文</a></span> -<span><a href="" target="_blank">可复制全文</a></span>
            -<span><a href="" target="_blank">法律状态</a></span> - <span><a href="" target="_blank">引证/被引证</a></span> -<span><a
                        href="" target="_blank">同族专利</a></span> - <span><a href="" target="_blank">分案专利</a></span> -<span><a
                        href="" target="_blank">复审无效</a></span> - <span><a href="" target="_blank">审查答复意见</a></span>
            - <span><a href="" target="_blank">信息查询</a></span></p>
    </dl>
</#list>