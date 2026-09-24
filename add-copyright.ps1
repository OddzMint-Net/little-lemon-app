# add-copyright.ps1
# Inserts a short OddzMint copyright header into every .kt file under a target directory,
# skipping any file that already contains "Copyright" near the top.
#
# Usage (from PowerShell, in your project root or anywhere):
#   .\add-copyright.ps1 -TargetDir "app\src\main\java"

param(
    [Parameter(Mandatory = $true)]
    [string]$TargetDir
)

$year = (Get-Date).Year

$header = @"
/*
 * Copyright (c) $year OddzMint.
 * Licensed under the MIT License - see LICENSE file in the project root.
 */

"@

if (-not (Test-Path $TargetDir)) {
    Write-Host "Directory not found: $TargetDir" -ForegroundColor Red
    exit 1
}

$files = Get-ChildItem -Path $TargetDir -Recurse -Filter *.kt

$updated = 0
$skipped = 0

foreach ($file in $files) {
    $content = Get-Content -Path $file.FullName -Raw -ErrorAction SilentlyContinue

    if ($null -eq $content) {
        continue
    }

    # Skip files that already have a copyright header near the top
    $firstLines = ($content -split "`n" | Select-Object -First 5) -join "`n"
    if ($firstLines -match "Copyright") {
        Write-Host "Skipping (already has header): $($file.FullName)" -ForegroundColor Yellow
        $skipped++
        continue
    }

    $newContent = $header + $content
    Set-Content -Path $file.FullName -Value $newContent -NoNewline
    Write-Host "Updated: $($file.FullName)" -ForegroundColor Green
    $updated++
}

Write-Host ""
Write-Host "Done. Updated $updated file(s), skipped $skipped file(s) that already had a header." -ForegroundColor Cyan
