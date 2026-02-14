# assignment2-security-testing

COMP-3021 Assignment 2 – Security Testing with GitHub Actions.
author : khushdeep kaur
## What to show 

- **Repo:** Open this repository on GitHub.
- **Code:** The 3 sample files (all are provided): `assignment1_code_sample_1.py`, `assignment1_code_sample_2.ts`, `assignment1_code_sample_3.java`.
- **Security:** Code scanning (CodeQL) is enabled.
- **Actions:** Show successful runs for **CodeQL**, **Lint Code Base** (SuperLinter), and **Bandit**.
- **Workflows:** Under `.github/workflows` – `bandit.yml` and `super-linter.yml`.

**What I did:** Created the repo, enabled default CodeQL, added SuperLinter and Bandit workflows, and uploaded  three samples files are provided so all three scanners run on them.


## Reflection questions

**Do these results match what you found in your previous peer review? Why or why not?**  
 In peer review i found hardcoded credentials, SQL injection, and command injection. CodeQL and Bandit report similar issues, so the kinds of problems match; the exact lines and wording may differ.

**Do you think they caught all the vulnerabilities? Why or why not?**  
Probably not all. Each tool has different rules and focus. Using several tools catches more types of issues, but I wouldn’t assume every vulnerability was found.

**Why is using multiple code scanners better than using one?**  
Each tool looks at different things CodeQL for security patterns, Bandit for Python, SuperLinter for style and some bad practices. Using more than one gives broader coverage and reduces the chance of missing something one tool doesn’t check.