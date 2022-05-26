<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class TypeCat extends Model
{
    use HasFactory;
    protected $fillable = [
        'title',
        'idResponsable'];
    
}
