<#import "parts/common.ftl" as com>

<@com.page>
List of Users

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbod>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">Edit</a> </td>
        </tr>
    </#list>
    </tbod>
</table>
</@com.page>