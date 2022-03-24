<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class borad extends Model
{
    use HasFactory;
    protected $table = 'borads';
    protected $fillable = [
        "nom",
        "post",
        "image",
        "idClub"
    ];
}
