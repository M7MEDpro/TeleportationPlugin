# 🚀 Teleportation Plugin

A comprehensive teleportation system for Minecraft servers with MongoDB integration, featuring homes, spawn teleportation, and player-to-player teleportation requests.

## ✨ Features

### 🏠 Home System
- **🎯 Multiple Homes**: Players can set up to 3 homes (configurable)
- **💾 MongoDB Storage**: Persistent data storage with async operations
- **🌍 Multi-World Support**: Works across all server worlds
- **⚡ Smart Caching**: Fast access with in-memory caching
- **⏱️ Cooldown Protection**: Configurable cooldowns to prevent spam

### 🌟 Spawn System
- **🏃 Quick Access**: Instant teleportation to world spawn
- **⏰ Cooldown Management**: Configurable delays between uses
- **💬 Custom Messages**: Fully customizable player feedback

### 📨 Teleportation Requests
- **🤝 Player Requests**: Send teleportation requests to other players
- **✅ Accept/Cancel System**: Full control over incoming requests
- **⏳ Auto-Expiration**: Requests automatically expire after set time
- **🛡️ Spam Protection**: Built-in cooldowns prevent request spam

### 👑 Admin Tools
- **🎯 Direct Teleportation**: Admin command to teleport to any player
- **🔐 Permission-Based**: Secure access control system

## 🎮 Commands

| Command | Description | Permission |
|---------|-------------|------------|
| 🏠 `/sethome <name>` | Set a home location | Default |
| 🏡 `/home <name>` | Teleport to home | Default |
| 🗑️ `/rmhome <name>` | Remove a home | Default |
| 🌍 `/spawn` | Teleport to spawn | Default |
| 👑 `/tp <player>` | Teleport to player | `tpplugin.tp` |
| 📤 `/tpr <player>` | Send teleport request | Default |
| ✅ `/tpa <player>` | Accept teleport request | Default |
| ❌ `/tpc <player>` | Cancel teleport request | Default |

## 📥 Installation

### 📋 Requirements
- 🎮 Minecraft Server (Bukkit/Spigot/Paper 1.13+)
- ☕ Java 8 or higher
- 🍃 MongoDB 3.6+

### 🚀 Setup
1. ⬇️ Download the plugin JAR file
2. 📁 Place it in your server's `plugins` folder
3. 🗄️ Ensure MongoDB is running (default: `localhost:27017`)
4. ▶️ Start your server
5. ⚙️ Configure the plugin in `plugins/TeleportationPlugin/config.yml`
6. 🔄 Restart the server

## ⚙️ Configuration

```yaml
home:
  max-homes: 3
  teleport-home:
    cooldown: 30  # cooldown in seconds
    cooldown-message: "&cYou are on cooldown. Please wait %seconds% seconds."
  messages:
    set-home: "&aYour home has been set."
    max-home: "&cYou have reached the max."
    teleport-home: "&bTeleporting to your home..."
    no-home: "&eYou haven't set a home with that name yet."
    delete-home: "&cYou have deleted home"

spawn:
  messages:
    teleport: "&bTeleporting to spawn..."
    cooldown: "&cYou are on cooldown. Please wait %seconds% seconds."
  cooldown: 30

tp:
  messages:
    teleport-to-player: "&bTeleported to %target%"

tpr:
  cooldown: 30
  expiretime: 30
  messages:
    send: "&b tp request has been send to %target%"
    send-to: "&b you have a tp request from %player%"
    accept: "&b accepted request from %player%"
    acceptsender: "%player% has accepted your request"
    cooldown: "&cYou are on cooldown. Please wait %seconds% seconds."
    expire: "&b expired rquest from %player%"
    expiresender: "&b expired rquest send to %player%"
    error-norequestsend: "error %player% didn't send you a request"
    cancel: "&cYou cancelled the teleport request to %player%"
    cancelsender: "&c%player% cancelled the teleport request"
```

## 🏗️ Database Structure

The plugin uses MongoDB with the following schema:

```javascript
{
  "_id": ObjectId,
  "uuid": "player-uuid-string",
  "homename": "home-name",
  "x": 123.45,
  "y": 64.0,
  "z": -456.78,
  "worldname": "world"
}
```

**Database Details:**
- 🗄️ **Database**: `MyDB`
- 📦 **Collection**: `Teleportation`
- 🔗 **Connection**: `mongodb://localhost:27017`

## 🔐 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `tpplugin.tp` | Allows use of `/tp` command | OP only |

## 🛠️ Troubleshooting

### 🚨 Common Issues

**❌ Database Connection Failed**
- ✅ Ensure MongoDB is running
- ✅ Check connection string in code
- ✅ Verify database permissions

**❌ Commands Not Working**
- ✅ Check for plugin conflicts
- ✅ Verify permissions are set correctly
- ✅ Check console for error messages

**❌ Homes Not Saving**
- ✅ Check MongoDB connection
- ✅ Verify database write permissions
- ✅ Check console for database errors

**❌ Config Not Loading**
- ✅ Check YAML syntax
- ✅ Restart server after changes
- ✅ Verify file permissions

## 🔧 Technical Details

### 📚 Dependencies
- **Imperat Framework**: Command handling and dependency injection
- **MongoDB Java Driver**: Database operations
- **Bukkit/Spigot API**: Core Minecraft server integration

### ⚡ Performance Features
- **Async Database Operations**: Non-blocking database calls
- **Memory Caching**: Fast home retrieval
- **Optimized Queries**: Efficient MongoDB operations
- **Rollback Protection**: Safe error handling

## 📋 Version Compatibility

- 🎮 **Minecraft**: 1.13+
- ☕ **Java**: 8+
- 🍃 **MongoDB**: 3.6+
- 🔧 **Server**: Bukkit/Spigot/Paper

## 📖 Usage Examples

### 🏠 Setting Up Homes
```
/sethome base     # Set your main base
/sethome farm     # Set your farm location
/sethome mine     # Set your mining area
```

### 📨 Using Teleport Requests
```
Player1: /tpr Player2     # Send request
Player2: /tpa Player1     # Accept request
```

### 🌍 Quick Spawn Access
```
/spawn                    # Return to spawn instantly
```

---

<div align="center">

**🎯 Ready to enhance your server's teleportation experience?**

*Install the Teleportation Plugin today and give your players the freedom to move!*

</div>