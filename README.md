# PaperWare

![Gitlab pipeline status](https://img.shields.io/gitlab/pipeline-status/budplaza/budplaza-software-paper?logo=gitlab&style=flat-square)
![GitLab tag (latest by date)](https://img.shields.io/gitlab/v/tag/budplaza/budplaza-software-paper?style=flat-square)
![Meercode.io CI Score](https://api.meercode.io/badge/BudPlaza/Paper-ware?type=ci-score&lastDay=14)

This is the BudPlaza software for Paper, implementing some common stuff.
It also helps us to fight against Griefing since they are very common in CMP.

Please note that only this plugin is not sufficient to stop griefs. To better
stop griefs, or to deal with them, use other plugins that we recommend, such as
CoreProtect and WorldGuard.

README in Simplified Chinese is available [here](README_zh-CN.md).

## Usage

### Dependencies

- PaperMC Server (Spigot or Bukkit _does not work_)
- CommandAPI

### Building from Source

Clone the source code and execute `gradlew build` then `gradlew jar`. Then you should see a jar file
under `build/libs` folder.
Drop it into your `plugins` folder.

When cloning, we recommend you to specify `--depth=1` if you do not wish to
contribute, as this will take less disk space and less time to clone,
compared to cloning the whole history.

### Commands

You can use the built-in Minecraft command helper to learn the syntax of the
commands of this plugin.

## Contributing

See our [contributing guidelines](docs/CONTRIBUTING.md).

## License

This project is licensed under [GNU Affero GPL 3.0](LICENSE) license, or any
later version you prefer.
