//
// This file is part of LuckyCrates, licensed under the MIT License.
//
// Copyright (c) 2021 Fefo6644 <federico.lopez.1999@outlook.com>
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//

package com.github.fefo.luckycrates.listeners;

import com.github.fefo.luckycrates.LuckyCratesPlugin;
import com.github.fefo.luckycrates.internal.CratesMap;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.UUID;

public final class ChunkLoadListener implements Listener {

  private final CratesMap cratesMap;

  public ChunkLoadListener(final LuckyCratesPlugin plugin) {
    this.cratesMap = plugin.getCratesMap();
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onChunkLoad(final ChunkLoadEvent event) {
    if (this.cratesMap.isEmpty()) {
      return;
    }

    for (final Entity entity : event.getChunk().getEntities()) {
      if (entity.getType() == EntityType.ARMOR_STAND) {
        final UUID uuid = entity.getUniqueId();
        if (this.cratesMap.containsKey(uuid)) {
          this.cratesMap.summon(uuid);
        }
      }
    }
  }
}
