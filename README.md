# PaperWare

NexusShift is the Paper plugin tailored for our own use.

## Usage

### Dependencies

- PaperMC Server (Spigot or Bukkit _does not work_ due to we referencing directly against Paper API)
  - This includes Kyori Adventure and we use all that now
- CommandAPI

### Building from Source

Clone the source code and execute `gradlew build` then `gradlew jar`. Then you should see a jar file
under `build/libs` folder.
Drop it into your `plugins` folder.

When cloning, we recommend you to specify `--depth=1` if you do not wish to
contribute, [as this will take less disk space](https://www.atlassian.com/git/tutorials/big-repositories) and less time to clone,
compared to cloning the whole history.

### Commands

You can use the built-in Minecraft command helper to learn the syntax of the
commands of this plugin.

## Contributing

See our [contributing guidelines](docs/CONTRIBUTING.md).

## License

This project is licensed under [GNU Affero GPL 3.0](LICENSE) license, or any
later version you prefer.
