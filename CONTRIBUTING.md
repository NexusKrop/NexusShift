# Contributing Guidelines

First, thank you for interesting to contribute to this project. As of
2022/01/25, there is only one person working on this project.

If you are previously a player from GreenCreative, EnderShop or RelaperStack,
thanks for coming back. If you found a feature that has no replacements in the
original data-packs, please open an issue.

## Reporting an Issue

If you plan to report issue, follow the guideline here.

### Bugs

Please make sure you can reproduce with the latest revision found in the `main`
branch. If you are not using the latest revision, please [build again from source](README.md#building-from-source), using
the latest revision, and verify it.

If we cannot reproduce your issue with the latest revision, or it's already fixed, your issue will be closed
and labelled as `state::invalid`.

If you report the bug for your own contribution **make sure** the issue you
reported has been marked as `state::ready` before creating the Merge Request.

### Feature requests

Please make sure the feature you are requesting does not present in the current
revision found in the `main` branch. If you want improvements on existing
feature(s) you'll have to open an [improvement-typed](#enhancements--improvements) issue.

Please describe what you exactly want / expect how it would work.

### Enhancements / Improvements

If you want a non-existing (new) feature you'll have to post [a feature request](#feature-requests).

Please describe what improvement you want and how exactly you expect how the improvement works
on the End-User side.

## Contributing Code

### Setting up Environment

Prior to hacking on this project you'll need to set up your environment.
Do not skip this part even if you believe you have properly set up your
environment.

#### Software

You'll need these:

- OpenJDK version 17.* with HotSpot JVM
  - We recommend Adoptium or official builds
- IntelliJ IDEA (any of Community or Ultimate should do), as least 2021.3.
  - With Minecraft Development plugin
- A testing server instance (install one on your development PC)
  - Minecraft version 1.18.1 
  - Must be PaperMC; Spigot simply don't work here
  - Windows or GNU/Linux (Mac, especially M1, and non-GNU Linux distributions or Linux/libre are not supported)
  - CommandAPI (latest)
- Minecraft: Java Edition

#### Installing instructions

- First, get your Minecraft ready (up and running) with version 1.18.1. If your launcher supports, this will be the latest version available as of 2021/1/25
- Download and install IntelliJ IDEA [here](https://www.jetbrains.com/idea/).
- Download and install Paper [here](https://papermc.io/downloads). Run it from a command line (we recommend using a script).
- Download and install CommandAPI [here](https://commandapi.jorel.dev/). Drop it into the `plugins` folder of your test instance.
- Clone the project. Available on the project page.

### Forking

We recommend you just fork the project and contribute back to upstream (us).

If you felt your fork ready to merge, create a Merge Request.

### Merge Requests

You should submit any contributions as a Merge Request if you are not a DevFish.

For major changes and bugfixes we require you to create an issue before creating a Merge Request.
