import React, { forwardRef } from "react";
import * as ScrollAreaPrimitive from "@radix-ui/react-scroll-area";
import { cn } from "../../lib/utils";

export const ScrollArea = forwardRef(({ className, children, ...props }, ref) => (
  <ScrollAreaPrimitive.Root ref={ref} className={cn("relative overflow-hidden", className)} {...props}>
    <ScrollAreaPrimitive.Viewport className="h-full w-full rounded-[inherit]">
      {children}
    </ScrollAreaPrimitive.Viewport>

    <ScrollAreaPrimitive.Scrollbar
      orientation="vertical"
      className="flex touch-none select-none p-0.5 bg-transparent transition-colors hover:bg-black/10"
    >
      <ScrollAreaPrimitive.Thumb className="flex-1 rounded-full bg-border" />
    </ScrollAreaPrimitive.Scrollbar>

    <ScrollAreaPrimitive.Scrollbar
      orientation="horizontal"
      className="flex touch-none select-none p-0.5 bg-transparent transition-colors hover:bg-black/10"
    >
      <ScrollAreaPrimitive.Thumb className="flex-1 rounded-full bg-border" />
    </ScrollAreaPrimitive.Scrollbar>

    <ScrollAreaPrimitive.Corner className="bg-border" />
  </ScrollAreaPrimitive.Root>
));
