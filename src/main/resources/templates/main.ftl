<#import "parts/common.ftl" as com>
<#import "parts/login.ftl" as log>

<@com.page>
<div>
    <@log.logout></@log.logout>
</div>
<div>
    <form method="post">
        <input type="text" name="text" placeholder="Ввведите сообщение">
        <input type="text" name="tag" placeholder="Teг">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Добавить</button>
    </form>
</div>

<div>Список сообщений</div>
<form method="get" action="/main">
    <input type="text" name="filter" value="${filter!}">
    <button type="submit">Найти</button>
</form>
<#list Messages! as message>
<div>
    <b>${message.id}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${message.authorName}</strong>
</div>
<#else>
No messages
</#list>
</@com.page>
