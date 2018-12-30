# SelenX
Write selenium tests using XML
## Examples

### Layout example
```xml
<layouts>
    <layout id="siteLayout">
        
        <po id="footer" css="#footer">
            <el id="cookiePolicy" css="a['/cookie/policy']" />
            <el id="privacy" css="a['/privacy']" />
            <el id="terms" css="a['/terms']" />
        </po>
    </layout>
    
    <layout id="notLoggedInLayout" extends="siteLayout">
        <po id="header" css="#header">
            <el id="logo" css="a" />
            <el id="search" css="name['query']" />
            <el id="home" css="ul a[href='/']" />
            <el id="login" css="ul a[href='/login']" />
            <el id="createAccount" css="ul a[href='/create/account']" />
        </po>
    </layout>

    <layout id="loggedInLayout" extends="siteLayout">
        <po id="header" css="#header">
            <el id="profile" css="ul a[href='/my/profile']" />
            <el id="logout" css="ul a[href='/logout']" />
        </po>
    </layout>
</layouts>

```
### Page example
```xml
<pages>
    <page id="homePage" layout="notLoggedInLayout">
        <po id="content">
            <el id="forgotPassword" css="a[href='/forgot/password']" />
            
            <po id="login" css="#login">
                <el id="email" css="[name='email']" />
                <el id="password" css="[name='password']" />
                <el id="rememberme" css="[name='rememberme']" />
                <el id="submit" css="[type='submit']" />
            </po>
            
            <po id="register" css="#register">
                <el id="email" css="[name='email']" />
                <el id="password" css="[name='password']" />
                <el id="reTypePassword" css="[name='reTypePassword']" />
                <el id="agreeWithTerms" css="[name='agreeWithTerms']" />
                <el id="submit" css="[type='submit']" />
            </po>
        </po>
    </page>
    
    <page id="logedInHomePage" layout="loggedInLayout">
        
    </page>
</pages>
```
### Test templates example
```xml
<?xml version="1.0" encoding="UTF-8"?>
<templates>
    <template id="loginTemplate">
        <type el="login email" value="test53@stasha.info" />
        <type el="login password" value="password" />
        <select el="login rememberme" value="false" />
        <mouse el="login submit" />
        <expected type="text" css="title">Admastic - Latest ads</expected>
    </template>
</templates>
```

### Site example
```xml
<site baseUri="http://localhost:8080/">
    <import file="core/layout.xml" />
    <import file="pages/home.xml" />
    <import file="core/templates.xml" />
</site>

```
### Tests examples
```xml
<tests>
    <test id="loginTest">
        <navigate url="/" return="homePage" />
        <mouse type="click" el="header home" />
        <expected type="text" css="title">Admastic</expected>
        
        <import template="loginTemplate" />

        <wait el="header home" until="isVisible" timeout="200" />
    </test>
    
    
    <test id="logoutTest">
        <navigate url="/" return="logedInHomePage" />
        <mouse el="header logout" return="homePage" />
        <expected type="text" css="title">Admastic</expected>
    </test>
</tests>
```


