#!/bin/sh

echo "进入git目录"
cd ~/MyWork/git/leeshare/jigsaw
echo "fetch origin"
git fetch origin
echo "merge origin/master"
git merge origin/master
git status
echo "add *"
git add *
git status
echo "commit -m ''"
git commit -m "mac sth"
echo "push -f origin master"
git push -f origin master



