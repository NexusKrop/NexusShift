# PaperWare

![Gitlab pipeline status](https://img.shields.io/gitlab/pipeline-status/budplaza/budplaza-software-paper?logo=gitlab&style=flat-square)
![GitLab tag (latest by date)](https://img.shields.io/gitlab/v/tag/budplaza/budplaza-software-paper?style=flat-square)
![Meercode.io CI Score](https://api.meercode.io/badge/BudPlaza/Paper-ware?type=ci-score&lastDay=14)

本插件实现一些通用的服务器功能，同时提供对部分常见破坏行为进行防御和处理的方法和手段。

请注意单靠本插件并不能完全的处理破坏行为。我们建议你配合使用类似于 WorldGuard 和 CoreProtect 等插件。

## 如何使用

### 依赖项

- PaperMC 服务端（Spigot 和 Bukkit 不好使）
- CommandAPI

### 从源码编译

克隆源代码至本地后，在命令行下执行 `gradlew build`，然后再执行 `gradlew jar`；执行完成后 `build/libs` 文件夹下应该有一个 jar 文件，直接丢进 `plugins`。

注意不要使用错误的服务端，并且一定要安装 CommandAPI。

如果你不打算贡献代码，推荐在克隆时指定 `--depth=1` 以提高克隆速度，并减少磁盘占用（相较于完整克隆）。

### 命令

可以使用内置的 Minecraft 命令提示和自动完成了解命令用法。

## 贡献

请见[贡献指南](docs/CONTRIBUTING_zh-CN.md)。

## 许可证

本项目依 [GNU Affero GPL 3.0](LICENSE) 许可证授权。
