def call(String repoUrl=null, String branchName=null) {
    /*
        Performs a checkout of the repository.

        Args:
            repoUrl (str):      The repository URL that needs to be cloned.
            branchName (str):   The name of the branch.
    */
    if (! repoUrl) {
        repoUrl = "http://github.com/red-hat-storage/cephci.git"
    }

    if (! branchName) {
        branchName = "origin/master"
    }

    checkout(
        scm: [
            $class: 'GitSCM',
            branches: [[name: branchName]],
            extensions: [[
                $class: 'CleanBeforeCheckout',
                deleteUntrackedNestedRepositories: true,
            ], [
                $class: 'WipeWorkspace',
            ], [
                $class: 'CloneOption',
                depth: 1,
                noTags: true,
                shallow: true,
                timeout: 10,
                reference: ''
            ]],
            userRemoteConfigs: [[
                url: repoUrl
            ]]
        ],
        changelog: false,
        poll: false
    )
}

