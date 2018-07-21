<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="650" style="border-collapse: collapse;">
    <tr>
        <td align="left" bgcolor="#78ab46" style="padding: 40px 0 30px 30px;">
            <p>您好！</p>
            <p>非常高兴的通知您，您的 xxxx 帐号及公私钥已经创建成功，以下是您的公私钥，请妥善保管</p>
        </td>
    </tr>
    <tr>
        <td bgcolor="#eaeaea" style="padding: 40px 30px 40px 30px;">
            <p>privatetKey: ${privateKey}</p>
            <p>publicKey: ${publicKey}</p>
            <p>address: ${address}</p>
        </td>
    </tr>
    <tr>
        <td align="right" bgcolor="#777777" style="padding: 30px 30px 30px 30px;">
            <p>感谢您对我们的关注</p>
            <p>xxxx 团队</p>
        </td>
    </tr>
</table>

</body>
</html>