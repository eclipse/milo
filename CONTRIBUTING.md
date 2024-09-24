# Table of Contents

1. [Introduction](#introduction)
2. [Ways to Contribute](#ways-to-contribute)
3. [Legal Requirements](#legal-requirements)
   - 3.1 [Sign the Eclipse Contributor Agreement](#sign-the-eclipse-contributor-agreement)
   - 3.2 [Add Your GitHub Username](#add-your-github-username)
   - 3.3 [Sign-off on Commits](#sign-off-on-commits)
   - 3.4 [Configuring Git Email](#configuring-git-email)
4. [Making Your Changes](#making-your-changes)
   - 4.1 [Fork the Repository](#fork-the-repository)
   - 4.2 [Create a New Branch](#create-a-new-branch)
   - 4.3 [Include a License Header](#include-a-license-header)
   - 4.4 [Test Your Changes](#test-your-changes)
5. [Submitting the Changes](#submitting-the-changes)
6. [After Submitting](#after-submitting)
7. [License Header](#license-header)

# How to contribute to Eclipse Milo

First of all, thank you for considering a contribution to Eclipse Milo. We really appreciate the time and effort you want to
spend improving the project, and we can use the help :-)

Here is a (non-exclusive, non-prioritized) list of things you might be able to help us with:

* documentation (Getting Started guide, Examples, etc...)
* bug reports
* bug fixes
* code quality improvements
* features (both ideas and code are welcome)

## Legal Requirements

Before you can get started an annoying but necessary bureaucratic requirement must be satisfied.


Milo is an [Eclipse IoT](https://iot.eclipse.org) project and as such is governed by the Eclipse Development process.
This process helps us in creating great open source software within a safe legal framework.

As a contributor the following preliminary steps are required in order for us to be able to accept your contribution:

* Sign the [Eclipse Contributor Agreement](https://www.eclipse.org/legal/ECA.php).
In order to do so:

  * Obtain an Eclipse Foundation user ID. Anyone who currently uses Eclipse Bugzilla or Gerrit systems already has one of those.
If you don't already have an account simply [register on the Eclipse web site](https://accounts.eclipse.org/user/register).
  * Once you have your account, log in to the [accounts portal](https://accounts.eclipse.org/), select *Eclipse Contributor Agreement* from the box on the right.

* Add your GitHub username to your Eclipse Foundation account. Log in to Eclipse and go to [my account](https://accounts.eclipse.org/), select *Edit Profile*.

The easiest way to contribute code/patches/whatever is by creating a GitHub pull request (PR).
When you do make sure that you *Sign-off* your commit records using the same email address used for your Eclipse account.

You do this by adding the `-s` flag when you make the commit(s), e.g.

    $> git commit -s -m "Shave the yak some more"

If the email address you used to sign the agreement differs from your global GitHub email, you'll need to configure your local Git environment. You can set the email for your commits using:

    $> git config --global user.email "your-email@example.com"

Alternatively, if you want to specify a different email for a specific repository, you can run the command inside that repository without the --global flag:

    $> git config user.email "your-email@example.com"

You can find all the details in the [Contributing via Git](http://wiki.eclipse.org/Development_Resources/Contributing_via_Git) document on the Eclipse web site.

## Making your Changes

* Fork the repository on GitHub
* Create a new branch for your changes
* Make your changes
* When you create new files make sure you include a proper license header at the top of the file (see License Header section below).
* Make sure you include test cases for non-trivial features
* Make sure the test suite passes after your changes
* Make sure checkstyle passes after your changes
* Commit your changes into that branch
* Use descriptive and meaningful commit messages
* If you have a lot of commits squash them into a single commit
* Make sure you use the `-s` flag when committing as explained above
* Push your changes to your branch in your forked repository

## Submitting the Changes

Submit a pull request via the normal GitHub UI.

## After Submitting

* Do not use your branch for any other development, otherwise further changes that you make will be visible in the PR.

## License Header

Please make sure new source files have license header like this:

````
/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */
````
