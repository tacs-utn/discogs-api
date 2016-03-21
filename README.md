The OpenShift `jbossews` cartridge documentation can be found at:

http://openshift.github.io/documentation/oo_cartridge_guide.html#tomcat

Add following to `~/.m2/settings.xml`:

> <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
>       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>       xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
>                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
>
> 	<pluginGroups>
> 	  <pluginGroup>org.apache.tomcat.maven</pluginGroup>
> 	</pluginGroups>
>
> </settings>