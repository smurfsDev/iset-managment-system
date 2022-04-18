<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class board extends Model
{
    use HasFactory;
    protected $table = 'boards';
    protected $fillable = [
        "nom",
        "post",
        "image",
        "idClub"
    ];
}
