package com.utils.brs;

import com.utils.type.Node.TreeNode;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈基本邮件信息〉
 *
 * @author Administrator
 * @create 2019/5/6 0006 18:03
 * @since 1.0.0
 * History:
 * <author>     <time>      <version>       <desc>
 * 作者姓名      修改时间       版本号           描述
 */
public class MailSimple {

    /**
     * 发生邮件（无附件）
     * @param content
     */
    public static void sendLawMail(String content,String title)
    {
        Email email = EmailBuilder.startingBlank()
                .from(title, "chiromath@sohu.com")
                .to("DreamLife", "chiromath@sohu.com")
                .withSubject("Law EntryReview")
                .withHTMLText(content)
                .buildEmail();

        MailerBuilder.withSMTPServer("smtp.sohu.com", 25, "chiromath", "118512Qq")
                .buildMailer().sendMail(email);

    }

    /**
     * 将得到的 Node 转为实际的邮件内容--无样式简单的 树结构分级 html
     * @param nodes
     * @return
     */
    public static String nodes2Content(List<TreeNode> nodes)
    {
        String content ="";
        int count = 0;
        if (nodes != null && nodes.size() != 0) {
            for (TreeNode node : nodes) {
                //最末节点增加序号
                if (null == node.getChildren()  || 0 == node.getChildren().size() ){
                    node.setName((++count)+". "+node.getName());
                }

                if("1" == node.getId() )
                {
                    content += "<h"+node.getId()+">"+node.getName() + "</h"+node.getId()+">" + nodes2Content(node.getChildren());
                }else{
                    content += "<ul><li>"+node.getName() + "</span></li><ul>"+ nodes2Content(node.getChildren()) +"</ul></ul>";
                }
            }
        }
        return content;
    }

}
