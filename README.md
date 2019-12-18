# Sample AEM project template

This is a project template for an AEM application to load assets from an external source. It demonstrates three approaches:
1. fixed-path application at http://localhost:4502/apps/dam-loader/tools/upload.html
2. Scheduled job that can poll and inject assets , see  SimpleScheduledTask
3. curl script to push aassets from a file system

## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, templates, runmode specific configs, etc.

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with

    mvn clean install -PautoInstallPackage
## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html
