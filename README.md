# xposed-qslock
Xposed module to disable the quicksettings view on lockscreen (modified for Pixel 4 / 4 XL)


I have no idea if anyone will ever read this. This is more for my own
reference. 100% credit goes to char101. I've never used GitHub before,
other than for browsing and such. I have no idea what I'm doing here
on the GitHub side of things and I hope I'm going about this the right
way and not stepping on anyone's toes. Anyway...

char101 created a nice Xposed module to lock QS pulldown while on lock
screen. Why it's still not a native feature in Android is beyond logic.
I really like having this mod on my devices, but on my Pixel 4 XL (and
my wife's Pixel 4), it would also prevent media player controls from
working from the lock screen. I don't have other devices to test on at
the moment, although based on the code I would assume this would be an
issue on other devices as well.

To fix this, I just removed DISABLE2_NOTIFICATION_SHADE references from
the code, leaving DISABLE2_NONE and DISABLE2_QUICK_SETTINGS untouched.
The mod still works perfectly as intended. Only tested on P4 / 4 XL on
Android 10 [Q]. I'm sure it would work on others as well, but since 
this was just a little personal project, I'm only mentioning it as such.

Again, this repository is really just for my reference. I've been looking
for a fully working mod for this on my new P4 and decided it would be
quicker to just do myself it / thanks to char101's work.
