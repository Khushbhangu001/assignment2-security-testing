# assignment2-security-testing

COMP-3021 Assignment 2  Security Testing with GitHub Actions.
author : khushdeep kaur
## What to show 

- **Repo:** Open this repository on GitHub.
- **Code:** The 3 sample files (all are provided): `assignment1_code_sample_1.py`, `assignment1_code_sample_2.ts`, `assignment1_code_sample_3.java'.
- **Security:** Code scanning (CodeQL) is enabled.
- **Actions:** Show successful runs for **CodeQL**, **Lint Code Base** (SuperLinter), and **Bandit**.
- **Workflows:** Under `.github/workflows`  `bandit.yml` and `super-linter.yml`.

**What I did:** Created the repo, enabled default CodeQL, added SuperLinter and Bandit workflows, and uploaded  three samples files are provided so all three scanners run on them.


## Reflection questions

**Do these results match what you found in your previous peer review? Why or why not?**  
 In peer review i found hardcoded credentials, SQL injection, and command injection. CodeQL and Bandit report similar issues, so the kinds of problems match; the exact lines and wording may differ. 
 
 Mostly yes, because the same kinds of insecure patterns show up: hardcoded credentials, SQL injection , command injection (using exec/shell), and HTTP instead of HTTPS. The only reason the results may look different is that my  Python file (assignment1_code_sample_1.py) is already fixed, so scanners won’t find those old Python issues anymore, but they can still flag the same patterns in the TypeScript/Java files.

**Do you think they caught all the vulnerabilities? Why or why not?**  
Probably not all. Each tool has different rules and focus. Using several tools catches more types of issues, but I wouldnt assume every vulnerability was found.

No. Scanners mostly catch known static patterns and they have limits:
Bandit only scans Python, so it would not catch TypeScript/Java issues.
SuperLinter is mainly lint/style, not deep security logic.
CodeQL is strong, but it can still miss things like business-logic flaws, missing authorization, insecure configuration/secrets outside code, and issues that don’t match its rules.

**Why is using multiple code scanners better than using one?**  
Each tool looks at different things CodeQL for security patterns, Bandit for Python, SuperLinter for style and some bad practices. Using more than one gives broader coverage and reduces the chance of missing something one tool doesnt check.

Because each tool covers different languages and different kinds of problems. Using multiple scanners gives broader coverage (Python + TS + Java), reduces the chance of miss something one tool doesn’t check, and overlapping findings increase confidence that an issue is real.


## Assignment 3  AppScan CodeSweep

For this assignment I added HCL AppScan CodeSweep to both my IDE and my GitHub CI:

- **VS Code plugin:** Installed the HCL AppScan CodeSweep extension in VS Code (with a recent Java runtime) 
and opened my previous assignment repo (`assignment2-security-testing`). When I saved `VulnerableApp.java`, 
CodeSweep automatically scanned the file and reported 4 issues in the CodeSweep Security Issues view (OS command injection, 
potential SQL injection, hardcoded secrets, and a DoSrelated pattern). This demonstrates the plugin is installed and working.

- **GitHub Action:** Created a new workflow file `.github/workflows/codesweep.yml` using the official `HCL-TECH-SOFTWARE/appscan-codesweep-action@v2.1` action. 
The workflow runs on `pull_request` and uses `actions/checkout@v4` with `fetch-depth: 0`. I also set the repository Workflow permissions to **Read and write** so the action can create annotations on pull requests.

- **Triggering a scan:** Created a branch `codesweep-test`, made a small change to `VulnerableApp.java`, and opened a pull request into `main`. 
This triggered the **HCL AppScan CodeSweep** GitHub Action, which completed successfully and reported findings alongside my existing CodeQL, Bandit, and SuperLinter checks.

**Reflection : comparison with previous scanners:**  
CodeSweep feels faster and more interactive than CodeQL and Bandit because it scans files on save and highlights problems directly in the editor. 
The GitHub Action version of CodeSweep runs in a similar time to my existing workflows but focuses on clear pattern-based issues (like injection and hardcoded secrets). 
CodeQL still gives deeper multi-language analysis and Bandit focuses on Python, so using CodeSweep together with them provides broader coverage and quicker feedback while I am writing code.


