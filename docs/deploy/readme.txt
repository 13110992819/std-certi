在装有java环境里
1，切换到DatGen.java目录,比如E目录
2，E:\>javac -d ./ DatGen.java
3，E:\>java DatGen '{"ipList":["127.0.0.1"],"codeList":["799001"]}'
4，拷贝当前目录下的“config.dat”到项目“{项目路径}\src\main\resources”
5，验证：项目跑起来。在浏览器输入“http://IP:端口/项目名/api”，出现版本信息即成功。

cd D:\workspace-work-20150824\xn-idAuth\docs\deploy
javac -d ./ DatGen.java
java DatGen '{"ipList":["127.0.0.1"],"codeList":["798001"]}'

部署步骤：
1，切换到本地tomcat部署包所在目录,例如
  cd /Users/myb858/Documents/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp11/wtpwebapps/

2，打包
  rm -rf certi.tar.gz
  tar zcvf certi.tar.gz std-certi/
  scp -P57652 ./certi.tar.gz root@120.55.113.192:/home/
  
  
3，部署
  ssh root@120.55.113.192 -p 57652
  
  cd /home/tomcat_STD_certi/webapps/
  rm -rf certi.tar.gz
  cp ./std-certi/WEB-INF/classes/application.properties .
  cp ./std-certi/WEB-INF/classes/config.properties .
  rm -rf std-certi/
  mv /home/certi.tar.gz .
  tar zxvf certi.tar.gz
  mv -f application.properties ./std-certi/WEB-INF/classes/
  mv -f config.properties ./std-certi/WEB-INF/classes/
4,起停tomcat_develop_account


http://120.55.113.192:8903/std-certi/api

