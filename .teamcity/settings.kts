import jetbrains.buildServer.configs.kotlin.v2018_2.*

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2019.1"

project {

    buildType(FeaturesPipeline)

    features {
        feature {
            id = "PROJECT_EXT_3"
            type = "IssueTracker"
            param("secure:password", "")
            param("name", "slucianol/controlme")
            param("pattern", """#(\d+)""")
            param("authType", "anonymous")
            param("repository", "https://github.com/slucianol/controlme")
            param("type", "GithubIssues")
            param("secure:accessToken", "")
            param("username", "")
        }
        feature {
            id = "PROJECT_EXT_4"
            type = "OAuthProvider"
            param("clientId", "Iv1.17d7baf7fa69899c")
            param("defaultTokenScope", "public_repo,repo,repo:status,write:repo_hook")
            param("secure:clientSecret", "credentialsJSON:d68b16ce-2a4c-472e-8815-e060e57d6fbc")
            param("displayName", "GitHub.com")
            param("gitHubUrl", "https://github.com/")
            param("providerType", "GitHub")
        }
    }
}

object FeaturesPipeline : BuildType({
    name = "Features Pipeline"

    params {
        param("teamcity.tool.NuGet.CommandLine.DEFAULT", "%")
    }

    vcs {
        root(DslContext.settingsRoot)

        cleanCheckout = true
        branchFilter = "+:testing*"
        excludeDefaultBranchChanges = true
    }

    steps {
        step {
            name = "Sonar Scanner"
            type = "simpleRunner"
            param("script.content", "sonar-scanner")
        }
        step {
            name = ".NET Restore"
            type = "dotnet.cli"
            param("paths", "ControlMe.WebApi/ControlMe.WebApi.sln")
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
            param("command", "restore")
            param("verbosity", "Normal")
        }
        step {
            name = ".NET Build"
            type = "dotnet.cli"
            param("args", "--no-restore")
            param("configuration", "Release")
            param("paths", "ControlMe.WebApi/ControlMe.WebApi.sln")
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
            param("command", "build")
            param("verbosity", "Normal")
        }
        step {
            name = ".NET Publish"
            type = "dotnet.cli"
            param("args", "--no-restore")
            param("outputDir", "../artifacts")
            param("configuration", "Release")
            param("paths", "ControlMe.WebApi/ControlMe.WebApi.csproj")
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
            param("command", "publish")
            param("verbosity", "Normal")
        }
        step {
            name = ".NET Test"
            type = "dotnet.cli"
            param("paths", "**/*.Tests/*")
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
            param("command", "test")
        }
    }

    triggers {
        trigger {
            type = "vcsTrigger"
        }
    }
})
