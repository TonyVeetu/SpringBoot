<#import "parts/common.ftl" as com>
<#import "parts/login.ftl" as log>

<@com.page>
${message?if_exists}
<@log.login "/login" false/>
</@com.page>