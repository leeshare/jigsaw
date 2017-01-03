#!/bin/sh

echo "进入git目录"
cd ~/MyWork/git/lee
echo "fetch origin"
git fetch origin
echo "merge origin/master"
git merge origin/master
echo "add *"
git add *
echo "commit -m ''"
git commit -m "mac sth"
echo "push -f origin master"
git push -f origin master



