<#import "parts/common.ftl" as com>

<@com.page>
User editor

<form action="/user" method="post" >
    <input type="text" name="user" value="${user.username}">
    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("check", "")}>${role}</label>
    </div>
    </#list>
    <input type="text" value="${user.id}" name="userID">
    <input type="text" value="${_csrf.token}" name="_csrf">
    <button type="submit">Save it!</button>
</form>
</@com.page>