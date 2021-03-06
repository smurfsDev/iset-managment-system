<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class about extends Model
{
    use HasFactory;
    protected $table = 'abouts';
    protected $fillable = [
        "longDescription",
        "idClub"
    ];

    public function club()
    {
        return $this->belongsTo(club::class);
    }
}
