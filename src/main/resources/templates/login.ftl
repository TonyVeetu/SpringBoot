<#import "parts/common.ftl" as com>
<#import "parts/login.ftl" as log>

<@com.page>
${message!}
    Login page
<@log.login "/login" />
    <a href="/registration">Add new user</a>
</@com.page>